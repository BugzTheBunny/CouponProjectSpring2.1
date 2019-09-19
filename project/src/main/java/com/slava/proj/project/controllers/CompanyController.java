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

import com.slava.proj.project.enums.CStatus;
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

	/*
	 * GET Requests
	 * 
	 * @getCustomers - returns the customer list
	 * 
	 * @getCustomerbyID
	 * 
	 * @getCustomerByName
	 * 
	 * @myCoupons - returns the coupons list for the current company
	 */
	@GetMapping("/customers")
	public Collection<User> getCustomers() {
		return userRepo.findAllByRole("ROLE_CUST");
	}

	@GetMapping("/custid/{id}")
	public Customer getCustomerById(@PathVariable("id") long id) {
		if (custRepo.findById(id) != null)
			return custRepo.findById(id);
		return new Customer(99999999, "Invalid Account", "Not a customer", "No Access", false);

	}

	@GetMapping("/custname/{username}")
	public Customer getCustomerByName(@PathVariable("username") String username) {
		if (custRepo.findByUsername(username) != null)
			return custRepo.findByUsername(username);
		return new Customer(99999999, "Invalid Account", "Not a customer", "No Access", false);
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
		cust.setRole("ROLE_CUST");
		custRepo.save(cust);
	}

	//////////////// PUT////////////////
	@PutMapping(path = "/updateUser", consumes = { "application/json" })
	public void updatePassword(Authentication authentication, @RequestBody String password) {
		User company = userRepo.findByUsername(authentication.getName());
		company.setPassword(password);
		userRepo.save(company);
	}

	/*
	 * DELETE Requests (Need to update)
	 * 
	 * @removeCoupon
	 * 
	 */
	@DeleteMapping("/dcoupon/{id}")
	public void removeCoupon(@PathVariable("id") long id) {
		if (couponRepo.findById(id) != null) {
			Coupon coupon = couponRepo.findById(id);
			coupon.setStatus(CStatus.REMOVED);
			couponRepo.save(coupon);
		}
	}

	@DeleteMapping("/dcustomer/{id}")
	public void deleteCustomer(@PathVariable("id") long id) {
		if (custRepo.findById(id) != null)
			custRepo.delete(custRepo.findById(id));

	}

}
