package com.slava.proj.project.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.slava.proj.project.enums.CStatus;
import com.slava.proj.project.models.Coupon;
import com.slava.proj.project.models.Customer;
import com.slava.proj.project.models.User;
import com.slava.proj.project.repo.CouponRepo;
import com.slava.proj.project.repo.CustomerRepo;
import com.slava.proj.project.repo.UserRepository;

@RestController("")
public class PublicController {

	/*
	 * This controller has the resources that should be open for everyone.
	 */

	@Autowired
	UserRepository userRepo;
	@Autowired
	CouponRepo couponRepo;
	@Autowired
	CustomerRepo custRepo;

	@GetMapping("/")
	public String welcome(Authentication authentication) {
		return "Logged in as: " + authentication.getName() + " Role: " + authentication.getAuthorities();
	}

	@GetMapping("/companies")
	public Collection<User> getCompanies() {
		return userRepo.findAllByRole("ROLE_COMP");
	}

	@GetMapping("/coupons")
	public Collection<Coupon> getAllCoupons() {
		return couponRepo.findAll();
	}

	@GetMapping("/coupons/{id}")
	public Coupon getCoupon(@PathVariable("id") long id) {
		return couponRepo.findById(id);
	}

	@GetMapping("/validcoupons")
	public Collection<Coupon> getAllValidCoupons() {
		Collection<Coupon> coupons = couponRepo.findAll();
		for (Coupon coupon : coupons) {
			if (coupon.getStatus() == CStatus.REMOVED || coupon.getStatus() == CStatus.EXPIRED) {
				coupons.remove(coupon);
			}
		}
		return coupons;

	}

	@PostMapping(path = "/newcustomer", consumes = { "application/json" })
	public void createCustomer(@RequestBody Customer cust) {
		cust.setRole("ROLE_CUST");
		custRepo.save(cust);
	}

}
