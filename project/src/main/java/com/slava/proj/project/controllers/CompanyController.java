package com.slava.proj.project.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("company")
public class CompanyController {

	@RequestMapping("/")
	public String welcome(Authentication authentication) {
		return "Hello Company Manager! " + authentication.getName() + " " + authentication.getAuthorities();
	}

}
