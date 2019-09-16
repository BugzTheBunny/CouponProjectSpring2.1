package com.slava.proj.project.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slava.proj.project.models.Coupon;
import com.slava.proj.project.models.User;
import com.slava.proj.project.repo.CouponRepo;
import com.slava.proj.project.repo.UserRepository;

@RestController
@RequestMapping("company")
public class CompanyController {

	@Autowired
	UserRepository userRepo;
	@Autowired
	CouponRepo couponRepo;

	@RequestMapping("")
	public String welcome(Authentication authentication) {
		return "Hello Company Manager! " + authentication.getName() + " " + authentication.getAuthorities();
	}

	////////////////// GET////////////////////////
	@GetMapping("/customers")
	public Collection<User> getCustomers() {
		return userRepo.findAllByRole("CUST");
	}

	@GetMapping("/userid/{id}")
	public User getUserById(@PathVariable("id") long id) {
		if (userRepo.findById(id).getRole().equalsIgnoreCase("CUST"))
			return userRepo.findById(id);
		return new User(99999999, "Invalid Account", "Not a customer", "No Access");

	}

	@GetMapping("/username/{username}")
	public User getUserByUsername(@PathVariable("username") String username) {
		if (userRepo.findByUsername(username).getRole().equalsIgnoreCase("CUST"))
			return userRepo.findByUsername(username);
		return new User(99999999, "Invalid Account", "Not a customer", "No Access");
	}

	@GetMapping("/coupons")
	public Collection<Coupon> getCoupons() {
		return couponRepo.findAll();
	}

	//////////////// POST///////////////
	@PostMapping(path = "/newcoup", consumes = { "application/json" })
	public void adduser(@RequestBody Coupon coup) {
		couponRepo.save(coup);
	}

	//////////////// DELETE/////////////
	@DeleteMapping("/dcoupon/{id}")
	public void removeCoupon(@PathVariable("id") long id) {
		couponRepo.delete(couponRepo.findById(id));
	}

	@DeleteMapping("/duser/{id}")
	public void deleteUser(@PathVariable("id") long id) {
		if (userRepo.findById(id).getRole().equalsIgnoreCase("CUST")) {
			userRepo.delete(userRepo.findById(id));
		} else {
			System.err.println("Not a customer");
		}

	}

}
