package br.com.diego.payflow.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.diego.payflow.controller.records.ApiResponse;
import br.com.diego.payflow.controller.records.OrderResponse;
import br.com.diego.payflow.controller.records.PaginationResponse;
import br.com.diego.payflow.service.OrderService;

@RestController
public class OrderController {

	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@GetMapping("/customers/{customerId}/orders")
	public ResponseEntity<ApiResponse<OrderResponse>> listOrders (@PathVariable("customerId") Long customerId,
			@RequestParam (name = "page", defaultValue = "0") Integer page, 
			@RequestParam (name = "pageSize", defaultValue = "0") Integer pageSize){

		var pageResponse = orderService.findAllByCustomerId(customerId, PageRequest.of(page, pageSize));
		
		return ResponseEntity.ok(new ApiResponse<>(pageResponse.getContent(), PaginationResponse.fromPage(pageResponse)));
	}
}
