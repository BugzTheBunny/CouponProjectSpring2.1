package com.slava.proj.project.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("company")
public class CompanyController {
	
	@RequestMapping("test")
	public String test() {
		return "Company working!";
	}

}
