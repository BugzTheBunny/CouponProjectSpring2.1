package com.slava.proj.project.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("customer")
public class CustomerController {

	@RequestMapping("/")
	public String welcome(Authentication authentication) {
		return "Hello Customer! " + authentication.getName() + " " + authentication.getAuthorities();
	}

}
