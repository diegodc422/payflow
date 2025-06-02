package br.com.diego.payflow.controller.records;

import java.math.BigDecimal;

import br.com.diego.payflow.config.model.OrderEntity;

public record OrderResponse(Long orderId, Long customerId, BigDecimal Total) {
	
	   public static OrderResponse fromEntity (OrderEntity entity) {
		   return new OrderResponse(entity.getOrderId(), entity.getCustomerId(), entity.getTotal());
	}
}
