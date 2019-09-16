package com.slava.proj.project.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slava.proj.project.models.Coupon;
import com.slava.proj.project.models.User;
import com.slava.proj.project.repo.CouponRepo;
import com.slava.proj.project.repo.UserRepository;

@RestController
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CouponRepo couponRepo;

	@RequestMapping("")
	public String welcome(Authentication authentication) {
		return "Hello Admin! " + authentication.getName() + " " + authentication.getAuthorities();
	}

	///// Getters//////////////////////////////////
	@GetMapping("/companies")
	public Collection<User> getCompanies() {
		return userRepo.findAllByRole("COMP");
	}

	@GetMapping("/userid/{id}")
	public User getUserById(@PathVariable("id") long id) {
		return userRepo.findById(id);
	}

	@GetMapping("/username/{username}")
	public User getUserByName(@PathVariable("username") String username) {
		return userRepo.findByUsername(username);
	}

	@GetMapping("/customers")
	public Collection<User> getCustomers() {
		return userRepo.findAllByRole("CUST");
	}

	@GetMapping("/coupons/{id}")
	public Coupon getCoupon(@PathVariable("id") long id) {
		return couponRepo.findById(id);
	}

	@GetMapping("/coupons")
	public Collection<Coupon> getCoupons() {
		return couponRepo.findAll();
	}

	//////////////////// POST////////////////////////////

	@PostMapping(path = "/newcomp", consumes = { "application/json" })
	public void adduser(@RequestBody User user) {
		user.setRole("COMP");
		userRepo.save(user);
	}

	/////////////////// UPDATE///////////////////////////
	@PutMapping(path = "/updateCoupon", consumes = { "application/json" })
	public void uCoupon(@RequestBody Coupon coupon) {
		couponRepo.save(coupon);
	}

	@PutMapping(path = "/updateUser", consumes = { "application/json" })
	public void uUser(@RequestBody User user) {
		userRepo.save(user);
	}

	//////////////////////// DELETE////////////////////////////
	@DeleteMapping("/dcoupon/{id}")
	public void removeCoupon(@PathVariable("id") long id) {
		couponRepo.delete(couponRepo.findById(id));
	}

	@DeleteMapping("/duserid/{id}")
	public void deleteUser(@PathVariable("id") long id) {
		userRepo.delete(userRepo.findById(id));
	}

	@DeleteMapping("/dusername/{username}")
	public void deleteUser(@PathVariable("username") String username) {
		userRepo.delete(userRepo.findByUsername(username));
	}
}
