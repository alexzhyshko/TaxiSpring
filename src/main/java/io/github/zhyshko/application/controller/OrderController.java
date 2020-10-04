package io.github.zhyshko.application.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.zhyshko.application.dto.request.RouteCreateRequest;
import io.github.zhyshko.application.dto.response.RouteDetails;
import io.github.zhyshko.application.dto.response.UserOrdersResponse;
import io.github.zhyshko.application.entity.Order;
import io.github.zhyshko.application.exception.RouteNotCreatedException;
import io.github.zhyshko.application.exception.SomethingWentWrongException;
import io.github.zhyshko.application.service.OrderService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;
	
	@PostMapping("/order/create")
	public ResponseEntity<Order> tryCreateRoute(@RequestHeader(required=true) String userLocale, @RequestBody RouteCreateRequest routeDetails) {
		Order orderCreated = orderService.tryCreateRouteLocalized(routeDetails, userLocale);
		return new ResponseEntity<>(orderCreated, HttpStatus.OK);
	}
	
	@PostMapping("/order/getRouteDetails")
	public ResponseEntity<List<RouteDetails>> getRouteDetails(@RequestHeader(required=true) String userLocale, @RequestBody RouteCreateRequest routeDetails) {
		List<RouteDetails> availableRoutesDetails = this.orderService.getAvailableRoutesDetailsLocalized(routeDetails, userLocale);
		return new ResponseEntity<>(availableRoutesDetails, HttpStatus.OK);
	}
	
	@GetMapping("/order/get/byUserId")
	public ResponseEntity<UserOrdersResponse> getOrdersByUserId(@RequestHeader(required=true) String userLocale, @RequestParam String type, @RequestParam UUID userId, @RequestParam Integer page) {
		UserOrdersResponse userOrdersPage = this.orderService.getOrdersByUserIdPaginatedLocalized(userId, page, type, userLocale);
		return new ResponseEntity<>(userOrdersPage, HttpStatus.OK);
	}
	
	public ResponseEntity<String> finishOrderById(@RequestHeader(required=true) String userLocale, @RequestParam Integer orderId) {
		if(!this.orderService.tryFinishOrderByIdLocalized(orderId, userLocale))
			throw new SomethingWentWrongException("Could not finish order");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	

	@ExceptionHandler({ RouteNotCreatedException.class})
    public ResponseEntity<String> handleRouteNotCreatedException(RouteNotCreatedException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler({ SomethingWentWrongException.class})
    public ResponseEntity<String> handleException(Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
	
}
