package br.com.diego.payflow.listener;

import static br.com.diego.payflow.config.RabbitMqConfig.ORDER_CREATED_QUEUE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import br.com.diego.payflow.records.OrderCreatedEvent;
import br.com.diego.payflow.service.OrderService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class OrderCreatedListener {
	
	private final Logger logger = LoggerFactory.getLogger(OrderCreatedListener.class);
	
	private final OrderService orderService;
	
	@RabbitListener(queues = ORDER_CREATED_QUEUE)
	public void Listen (Message<OrderCreatedEvent> message) {
		logger.info("Message Consumed: {} ", message);

		orderService.save(message.getPayload());
	}
}
