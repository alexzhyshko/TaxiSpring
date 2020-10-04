package io.github.zhyshko.application.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@RolesAllowed("ADMIN")
	@GetMapping("/getAllOrders")
	public String getAllOrders() {
		return "all orders";
	}
	
}
