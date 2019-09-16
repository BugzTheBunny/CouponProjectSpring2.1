package com.slava.proj.project.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("customer")
public class CustomerController {

	@RequestMapping("test")
	public String test() {
		return "Customer working!";
	}

}
