package com.slava.proj.project.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class AdminController {

	@RequestMapping("/")
	public String welcome(Authentication authentication) {
		return "Hello Admin! " + authentication.getName() + " " + authentication.getAuthorities();
	}

}
