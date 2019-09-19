package com.slava.proj.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slava.proj.project.enums.CStatus;
import com.slava.proj.project.models.CompanyWallet;
import com.slava.proj.project.models.Coupon;
import com.slava.proj.project.models.Customer;
import com.slava.proj.project.repo.CouponRepo;
import com.slava.proj.project.repo.CustomerRepo;
import com.slava.proj.project.repo.WalletRepo;

@RestController
@RequestMapping("customer")
public class CustomerController {

	@Autowired
	CouponRepo couponRepo;
	@Autowired
	CustomerRepo custRepo;
	@Autowired
	WalletRepo walletRepo;

	@GetMapping("")
	public String welcome(Authentication authentication) {
		return "Hello Customer! " + authentication.getName() + " " + authentication.getAuthorities();
	}

	/*
	 * GET Requests
	 * 
	 * @myCoupons - returns the current logged user coupons list
	 *
	 * @myInfo - return the current logged user credentials
	 *
	 */

	@GetMapping("/mycoupons")
	public List<Coupon> myCoupons(Authentication authentication) {
		List<Coupon> coupons = custRepo.findByUsername(authentication.getName()).getCoupons();
		return coupons;
	}



	/*
	 * POST Requests
	 * 
	 * @buyCoupons - Allows you to buy coupons (Inserting the ID of the coupons and
	 * the id of the customer into a join table)
	 * 
	 * @
	 */

	@PostMapping(path = "/buy", consumes = { "application/json" })
	public void buyCoupon(Authentication authentication, @RequestBody long id) {
		Customer cust = custRepo.findByUsername(authentication.getName());
		Coupon coup = couponRepo.findById(id);
		CompanyWallet wallet = walletRepo.findById(1);
		if (coup.getStatus() != CStatus.EXPIRED && coup.getAmount() > 0) {
			cust.addCoupon(couponRepo.findById(id));
			coup.setAmount(coup.getAmount() - 1);
			wallet.setIncome(wallet.getIncome() + coup.getPrice());
			wallet.setTransactions(wallet.getTransactions() + 1);
			walletRepo.save(wallet);
			custRepo.save(cust);
			couponRepo.save(coup);
		} else {
			System.err.println("Error");
		}

	}

	/*
	 * PUT Requests
	 * 
	 * @Allows the current logged customer to change his password (the username may
	 * be changed only by the Admin);
	 */

	@PutMapping(path = "/update", consumes = { "application/json" })
	public void updatePassword(Authentication authentication, @RequestBody String password) {
		Customer currCust = custRepo.findByUsername(authentication.getName());
		currCust.setPassword(password);
		custRepo.save(currCust);
	}

}
