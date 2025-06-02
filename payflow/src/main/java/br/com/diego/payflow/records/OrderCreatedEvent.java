package br.com.diego.payflow.records;

import java.util.List;

public record OrderCreatedEvent(Long orderCode, Long clientCode, List<OrderItemEvent> itens) { }
