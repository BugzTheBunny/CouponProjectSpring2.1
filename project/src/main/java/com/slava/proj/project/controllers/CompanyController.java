package com.slava.proj.project.controllers;

import java.util.Collection;
import java.util.List;

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
import com.slava.proj.project.models.Customer;
import com.slava.proj.project.models.User;
import com.slava.proj.project.repo.CouponRepo;
import com.slava.proj.project.repo.CustomerRepo;
import com.slava.proj.project.repo.UserRepository;

@RestController
@RequestMapping("company")
public class CompanyController {

	@Autowired
	UserRepository userRepo;
	@Autowired
	CouponRepo couponRepo;
	@Autowired
	CustomerRepo custRepo;

	@GetMapping("")
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

	@GetMapping("/mycoupons")
	public List<Coupon> myCoupons(Authentication authentication) {
		List<Coupon> coupons = userRepo.findByUsername(authentication.getName()).getCoupons();
		return coupons;
	}

	@GetMapping("/info")
	public User myInfo(Authentication authentication) {
		return userRepo.findByUsername(authentication.getName());
	}

	@GetMapping("/coupons")
	public Collection<Coupon> getCoupons() {
		return couponRepo.findAll();
	}

	//////////////// POST///////////////
	@PostMapping(path = "/newcoup", consumes = { "application/json" })
	public void createCoupon(Authentication authentication, @RequestBody Coupon coupon) {
		User company = userRepo.findByUsername(authentication.getName());
		couponRepo.save(coupon);
		company.createCoupon(coupon);
		userRepo.save(company);
	}

	@PostMapping(path = "/newcustomer", consumes = { "application/json" })
	public void addCustomer(@RequestBody Customer cust) {
		cust.setRole("CUST");
		custRepo.save(cust);
	}

	//////////////// PUT////////////////
	@PutMapping(path = "/updateUser", consumes = { "application/json" })
	public void updatePassword(Authentication authentication, @RequestBody String password) {
		User company = userRepo.findByUsername(authentication.getName());
		company.setPassword(password);
		userRepo.save(company);
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
