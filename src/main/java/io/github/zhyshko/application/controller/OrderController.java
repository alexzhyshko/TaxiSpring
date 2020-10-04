package io.github.zhyshko.application.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.zhyshko.application.dto.request.RouteCreateRequest;
import io.github.zhyshko.application.dto.response.RouteDetails;
import io.github.zhyshko.application.dto.response.UserOrdersResponse;
import io.github.zhyshko.application.entity.Order;

@RestController
public class OrderController {

	@PostMapping("/order/create")
	public ResponseEntity<Order> tryCreateRoute(@RequestBody RouteCreateRequest request) {
		
	}
	
	@PostMapping("/order/getRouteDetails")
	public ResponseEntity<List<RouteDetails>> getRouteDetails(@RequestBody RouteCreateRequest request) {
		
	}
	
	@GetMapping("/order/get/byUserId")
	public ResponseEntity<UserOrdersResponse> getOrdersByUserId(@RequestParam Optional<String> type, @RequestParam Optional<UUID> userId, @RequestParam Optional<Integer> page) {
		
	}
	
	public ResponseEntity<String> finishOrderById(@RequestParam Optional<Integer> orderId) {
		
	}
	
}
