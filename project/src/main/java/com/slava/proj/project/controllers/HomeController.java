package com.slava.proj.project.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("home")
public class HomeController {

	@GetMapping("/")
	public String welcome(Authentication authentication) {
		return "Logged in as: " + authentication.getName() + " Role: " + authentication.getAuthorities();
	}
}
