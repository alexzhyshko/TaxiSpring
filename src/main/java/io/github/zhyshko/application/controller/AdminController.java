package io.github.zhyshko.application.controller;

import java.util.Optional;

import javax.annotation.security.RolesAllowed;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.zhyshko.application.dto.response.UserOrdersResponse;

@RestController
@RolesAllowed("ADMIN")
@RequestMapping("/admin")
public class AdminController {

	
	@GetMapping("/admin/order/get/all")
	public UserOrdersResponse getAllOrdersSortedFiltered(@RequestParam(defaultValue = "false") Optional<Boolean> sort,
			@RequestParam(defaultValue = "false") Optional<Boolean> filter,
			@RequestParam Optional<String> sortBy,
			@RequestParam Optional<String> sortOrder,
			@RequestParam Optional<String> filterBy,
			@RequestParam Optional<String> value) {
		return new UserOrdersResponse();
	}
	
	
	
}
