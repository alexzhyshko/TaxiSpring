package io.github.zhyshko.application.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.zhyshko.application.dto.PaginationFilteringSortingDTO;
import io.github.zhyshko.application.dto.response.UserOrdersResponse;
import io.github.zhyshko.application.service.OrderService;
import lombok.RequiredArgsConstructor;

@RestController
@RolesAllowed("ADMIN")
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

	private final OrderService orderService;
	
	@GetMapping("/admin/order/get/all")
	public ResponseEntity<UserOrdersResponse> getAllOrdersSortedFilteredPagedLocalized(@RequestHeader(required=true) String userLocale, 
			@RequestParam(required = true) Boolean sort,
			@RequestParam(required = true) Boolean filter,
			@RequestParam(required = true) String sortBy,
			@RequestParam(required = true) String sortOrder,
			@RequestParam(required = true) String filterBy,
			@RequestParam(required = true) String value,
			@RequestParam(required = true) Integer page) {
		PaginationFilteringSortingDTO dto = PaginationFilteringSortingDTO.builder()
				.sort(sort)
				.filter(filter)
				.sortBy(sortBy)
				.sortOrder(sortOrder)
				.filterBy(filterBy)
				.value(value)
				.build();
		UserOrdersResponse paginatedResponse = orderService.getAllOrdersPagedAndSortedAndLocalized(dto, userLocale);
		return new ResponseEntity<>(paginatedResponse, HttpStatus.OK);
	}
	
	@ExceptionHandler({ Exception.class})
    public ResponseEntity<String> handleException(Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
	
}
