package com.slava.proj.project.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {

	@Id
	@Column
	@NotNull
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull
	private String username;
	@NotNull
	private String password;
	@NotNull
	@JsonIgnore
	private String role;
	@JsonIgnore
	@ManyToMany
	private List<Coupon> coupons;
	@NotNull
	@JsonIgnore
	@Column(columnDefinition = "BOOLEAN")
	private boolean isEnabled;

	public User() {

	}

	public User(long id, String username, String password, String role, boolean isEnabled) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.isEnabled = isEnabled;
	}

	public void setId(long id) {
		this.id = id;
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

	public void setRole(String roles) {
		this.role = roles;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

	public void createCoupon(Coupon coupon) {
		this.coupons.add(coupon);
	}

	public List<Coupon> getCoupons() {
		return coupons;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

}
