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

import io.github.zhyshko.application.converter.entity2dto.OrderEntityToDTOConverter;
import io.github.zhyshko.application.dto.Order;
import io.github.zhyshko.application.dto.request.OrderCreateRequest;
import io.github.zhyshko.application.dto.response.RouteDetails;
import io.github.zhyshko.application.dto.response.UserOrdersResponse;
import io.github.zhyshko.application.exception.RouteNotCreatedException;
import io.github.zhyshko.application.exception.SomethingWentWrongException;
import io.github.zhyshko.application.service.OrderService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;
	
	@PostMapping("/order/create")
	public ResponseEntity<Order> tryCreateOrder(@RequestHeader(required=true, name="User_Locale") String userLocale, @RequestBody(required=true) OrderCreateRequest orderDetails) {
		Order orderCreated = OrderEntityToDTOConverter.convertToDto(orderService.tryCreateOrder(orderDetails), userLocale);
		return new ResponseEntity<>(orderCreated, HttpStatus.OK);
	}
	
	@PostMapping("/order/getRouteDetails")
	public ResponseEntity<List<RouteDetails>> getRouteDetails(@RequestHeader(required=true, name="User_Locale") String userLocale, @RequestBody(required=true) OrderCreateRequest orderDetails) {
		List<RouteDetails> availableRoutesDetails = this.orderService.getAvailableRoutesDetailsByEveryCategory(orderDetails, userLocale);
		return new ResponseEntity<>(availableRoutesDetails, HttpStatus.OK);
	}
	
	@GetMapping("/order/get/byUserId")
	public ResponseEntity<UserOrdersResponse> getOrdersByUserId(@RequestHeader(required=true, name="User_Locale") String userLocale, @RequestParam(required=true) UUID userId, @RequestParam(required=true) Integer page) {
		UserOrdersResponse userOrdersPage = this.orderService.getOrdersByUserIdPaginated(userId, page, userLocale);
		return new ResponseEntity<>(userOrdersPage, HttpStatus.OK);
	}
	
	@GetMapping("/order/finish")
	public ResponseEntity<String> finishOrderById(@RequestHeader(required=true, name="User_Locale") String userLocale, @RequestParam(required=true) Integer orderId) {
		if(!this.orderService.tryFinishOrderById(orderId))
			throw new SomethingWentWrongException("Could not finish order");
		return new ResponseEntity<>("Order finished", HttpStatus.OK);
	}
	

	@ExceptionHandler({ RouteNotCreatedException.class})
    public ResponseEntity<String> handleRouteNotCreatedException(RouteNotCreatedException e) {
		e.printStackTrace();
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler({ SomethingWentWrongException.class})
    public ResponseEntity<String> handleException(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
	
}
