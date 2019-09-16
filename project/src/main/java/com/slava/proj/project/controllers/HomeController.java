package com.slava.proj.project.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("home")
public class HomeController {

	@RequestMapping("/")
	public String welcome(Authentication authentication) {
		return "Hello! " + authentication.getName() + " " + authentication.getAuthorities();
	}
}
