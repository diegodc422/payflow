package br.com.diego.payflow.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.diego.payflow.config.model.OrderEntity;
import br.com.diego.payflow.config.model.OrderItem;
import br.com.diego.payflow.controller.records.OrderResponse;
import br.com.diego.payflow.records.OrderCreatedEvent;
import br.com.diego.payflow.repository.OrderRepository;

@Service
public class OrderService {

	private final OrderRepository orderRepository;

	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public void save(OrderCreatedEvent event) {
		var entity = new OrderEntity();

		entity.setOrderId(event.orderCode());
		entity.setCustomerId(event.clientCode());
		entity.setItems(getOrderItems(event));
		entity.setTotal(getTotal(event));

		orderRepository.save(entity);
	}

	private BigDecimal getTotal(OrderCreatedEvent event) {
		return event.itens().stream().map(i -> i.preco().multiply(BigDecimal.valueOf(i.quantidade())))
				.reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
	}

	public static List<OrderItem> getOrderItems(OrderCreatedEvent event) {
		return event.itens().stream().map(i -> new OrderItem(i.produto(), i.quantidade(), i.preco())).toList();
	}

	public Page<OrderResponse> findAllByCustomerId(Long customerId, PageRequest pageRequest) {
		var orders = orderRepository.findAllByCustomerId(customerId, pageRequest);

		return orders.map(OrderResponse::fromEntity);
	}
}