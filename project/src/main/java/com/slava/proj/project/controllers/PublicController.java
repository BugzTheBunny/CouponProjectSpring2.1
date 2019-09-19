package com.slava.proj.project.controllers;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slava.proj.project.enums.CStatus;
import com.slava.proj.project.models.Coupon;
import com.slava.proj.project.models.User;
import com.slava.proj.project.repo.CouponRepo;
import com.slava.proj.project.repo.UserRepository;

@RestController("public")
public class PublicController {
	@Autowired
	UserRepository userRepo;
	@Autowired
	CouponRepo couponRepo;

	@GetMapping("/")
	public String welcome(Authentication authentication) {
		return "Logged in as: " + authentication.getName() + " Role: " + authentication.getAuthorities();
	}

	@GetMapping("/companies")
	public Collection<User> getCompanies() {
		return userRepo.findAllByRole("ROLE_COMP");
	}

	@GetMapping("/coupons")
	public List<Coupon> getAllCoupons() {
		return couponRepo.findAll();
	}

	@GetMapping("/validcoupons")
	public List<Coupon> getAllValidCoupons() {
		List<Coupon> coupons = null;
		coupons.addAll(couponRepo.findAllByStatusNot(CStatus.EXPIRED));
		coupons.addAll(couponRepo.findAllByStatusNot(CStatus.REMOVED));
		return coupons;
	}
}
