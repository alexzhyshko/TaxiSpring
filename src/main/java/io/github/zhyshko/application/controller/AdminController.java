package io.github.zhyshko.application.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Secured("ROLE_ADMIN")
	@GetMapping("/getAllOrders")
	public String getAllOrders() {
		return "all orders";
	}
	
}
