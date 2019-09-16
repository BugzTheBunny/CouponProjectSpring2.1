package com.slava.proj.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.slava.proj.project.models.Coupon;

public interface CouponRepo extends JpaRepository<Coupon, Long> {

	Coupon findById(long id);

}
