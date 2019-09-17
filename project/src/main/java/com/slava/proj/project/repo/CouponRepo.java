package com.slava.proj.project.repo;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.slava.proj.project.enums.CStatus;
import com.slava.proj.project.models.Coupon;

public interface CouponRepo extends JpaRepository<Coupon, Long> {

	Coupon findById(long id);

	List<Coupon> findAllByEnddateAfter(Date date);

	List<Coupon> findAllByStatusNot(CStatus cStatus);
}
