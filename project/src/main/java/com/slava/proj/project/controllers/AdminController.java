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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.slava.proj.project.models.Coupon;
import com.slava.proj.project.models.Customer;
import com.slava.proj.project.models.User;
import com.slava.proj.project.repo.CouponRepo;
import com.slava.proj.project.repo.CustomerRepo;
import com.slava.proj.project.repo.UserRepository;

@RestController
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CouponRepo couponRepo;
	@Autowired
	private CustomerRepo custRepo;

	@GetMapping("")
	public String welcome(Authentication authentication) {
		return "Hello Admin! " + authentication.getName();
	}

	/*
	 * GET Requests:
	 * 
	 * @getUserById
	 * 
	 * @getCustById
	 * 
	 * @getUserByName - For returning companies/ admins
	 * 
	 * @getCustomerByName
	 * 
	 * @getCoupons
	 * 
	 */

	@GetMapping("/userid/{id}")
	public User getUserById(@PathVariable("id") long id) {
		return userRepo.findById(id);
	}

	@GetMapping("/custid/{id}")
	public Customer getCustById(@PathVariable("id") long id) {
		return custRepo.findById(id);
	}

	@GetMapping("/username/{username}")
	public User getUserByName(@PathVariable("username") String username) {
		return userRepo.findByUsername(username);
	}

	@GetMapping("/custname/{username}")
	public Customer getCustomerByName(@PathVariable("username") String username) {
		return custRepo.findByUsername(username);
	}

	/*
	 * POST Requests
	 * 
	 * @addCompany
	 * 
	 * @addCustomer
	 * 
	 */

	@PostMapping(path = "/newCompany", consumes = { "application/json" })
	public void addCompany(@RequestBody User user) {
		user.setRole("ROLE_COMP");
		userRepo.save(user);
	}

	@PostMapping(path = "/newcustomer", consumes = { "application/json" })
	public void addCustomer(@RequestBody Customer cust) {
		cust.setRole("ROLE_CUST");
		custRepo.save(cust);
	}

	/*
	 * PUT Requests
	 * 
	 * @updateCoupon - (coupon id, JSon of the coupon info )
	 * 
	 * @updateUser - updates users only
	 * 
	 * @updateCusomer - updates customers only
	 * 
	 * @promote - random operation, can promote Company owner to an ADMIN, by ID
	 */
	@PutMapping(path = "/updateCoupon", consumes = { "application/json" })
	public void updateCoupon(@RequestParam long id, @RequestBody Coupon coupon) {
		Coupon update = couponRepo.findById(id);
		update.setAmount(coupon.getAmount());
		update.setEnddate(coupon.getEnddate());
		update.setMessage(coupon.getMessage());
		update.setPrice(coupon.getPrice());
		update.setTitle(coupon.getTitle());
		couponRepo.save(update);
	}

	@PutMapping(path = "/updateUser", consumes = { "application/json" })
	public void updateUser(@RequestBody User user) {
		userRepo.save(user);
	}

	@PutMapping(path = "/updateCust", consumes = { "application/json" })
	public void updateCustomer(@RequestBody Customer customer) {
		custRepo.save(customer);
	}

	@PutMapping(path = "/promote", consumes = { "application/json" })
	public void promote(@RequestBody long id) {
		User toPromote = userRepo.findById(id);
		toPromote.setRole("ROLE_ADMIN");
		userRepo.save(toPromote);
	}

	/*
	 * DELETE Requests
	 * 
	 * @----(Need to implements the status with spring security)----@
	 * 
	 * @removeCoupon - needs to be updated
	 * 
	 * @deleteUser
	 * 
	 * @deleteCustomerById
	 * 
	 * @deleteCustomerByName
	 * 
	 * @deleteUser
	 * 
	 */
	@DeleteMapping("/dcoupon/{id}")
	public void removeCoupon(@PathVariable("id") long id) {
		couponRepo.delete(couponRepo.findById(id));
	}

	@DeleteMapping("/duserid/{id}")
	public void deleteUserById(@PathVariable("id") long id) {
		userRepo.delete(userRepo.findById(id));
	}

	@DeleteMapping("/dusername/{username}")
	public void deleteUserByName(@PathVariable("username") String username) {
		userRepo.delete(userRepo.findByUsername(username));
	}

	@PutMapping("/validcustomer/{id}")
	public void customerValiditi(@PathVariable("id") long id) {
		if (custRepo.findById(id) != null) {
			Customer customer = custRepo.findById(id);
			if (customer.isEnabled() == true) {
				customer.setEnabled(false);
			} else {
				customer.setEnabled(true);
			}
			custRepo.save(customer);

		}
	}

	@PutMapping("/validcustomer/{username}")
	public void customerValidNByName(@PathVariable("username") String username) {
		if (custRepo.findByUsername(username) != null) {
			Customer customer = custRepo.findByUsername(username);
			if (customer.isEnabled() == true) {
				customer.setEnabled(false);
			} else {
				customer.setEnabled(true);
			}
			custRepo.save(customer);

		}
	}

}
