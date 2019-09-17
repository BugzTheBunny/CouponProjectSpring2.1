package com.slava.proj.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slava.proj.project.models.Coupon;
import com.slava.proj.project.models.Customer;
import com.slava.proj.project.repo.CouponRepo;
import com.slava.proj.project.repo.CustomerRepo;

@RestController
@RequestMapping("customer")
public class CustomerController {

	@Autowired
	CouponRepo couponRepo;
	@Autowired
	CustomerRepo custRepo;

	@GetMapping("")
	public String welcome(Authentication authentication) {
		return "Hello Customer! " + authentication.getName() + " " + authentication.getAuthorities();
	}

	@GetMapping("/mycoupons")
	public List<Coupon> myCoupons(Authentication authentication) {
		List<Coupon> coupons = custRepo.findByUsername(authentication.getName()).getCoupons();
		return coupons;
	}

	@PostMapping(path = "/buy", consumes = { "application/json" })
	public void buyCoupon(Authentication authentication, @RequestBody long id) {
		Customer cust = custRepo.findByUsername(authentication.getName());
		cust.addCoupon(couponRepo.findById(id));
		custRepo.save(cust);
	}

}
