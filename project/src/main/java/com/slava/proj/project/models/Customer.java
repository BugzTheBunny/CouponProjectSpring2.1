package com.slava.proj.project.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Customer {

	@Id
	private long id;
	@NotNull
	private String username;
	@NotNull
	private String password;
	@NotNull
	private String role = "CUST";
	@JsonIgnore
	@ManyToMany
	private List<Coupon> coupons;

	public Customer() {

	}

	public Customer(long id, String username, String password, String role) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void addCoupon(Coupon coupon) {
		this.coupons.add(coupon);
	}

}
