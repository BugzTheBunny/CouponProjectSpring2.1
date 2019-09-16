package com.slava.proj.project.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@RequestMapping("/home")
	public String home(Authentication authentication) {
		return "Hello! " + authentication.getName();
	}
}
