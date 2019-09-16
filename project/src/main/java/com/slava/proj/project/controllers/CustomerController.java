package com.slava.proj.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slava.proj.project.models.User;
import com.slava.proj.project.repo.CouponRepo;
import com.slava.proj.project.repo.UserRepository;

@RestController
@RequestMapping("customer")
public class CustomerController {

	@Autowired
	CouponRepo couponRepo;
	@Autowired
	UserRepository userRepo;

	@RequestMapping("")
	public String welcome(Authentication authentication) {
		return "Hello Customer! " + authentication.getName() + " " + authentication.getAuthorities();
	}

	@PostMapping(path = "/buy", consumes = { "application/json" })
	public void adduser(@RequestBody long id) {
		
	}

}
