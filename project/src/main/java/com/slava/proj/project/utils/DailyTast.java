package com.slava.proj.project.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.slava.proj.project.enums.CStatus;
import com.slava.proj.project.models.Coupon;
import com.slava.proj.project.repo.CouponRepo;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
public class DailyTast {

	@Autowired
	private CouponRepo cRepo;

	@Scheduled(initialDelay = 5000L, fixedDelay = 5000L)
	void update() {
		long amount = 0;
		LocalDateTime time = LocalDateTime.now();
		List<Coupon> outdated = cRepo.findAllByEnddateAfter(Date.valueOf(LocalDate.now()));
		List<Coupon> expired = cRepo.findAllByStatusNot(CStatus.ONSALE);
		/////////////////////////////////////////
		for (Coupon coupon : outdated) {
			if (coupon.getStatus() != CStatus.EXPIRED) {
				amount++;
				coupon.setStatus(CStatus.EXPIRED);
				cRepo.save(coupon);
			}
		}
		////////////////////////////////////////
		System.out.println("------- Number of updated coupons today: " + amount);
		System.out.println("Expired Coupons: \n" + expired);
		System.out.println("Outdated Coupons: \n" + outdated);
		System.out.println("Daily task working " + time);
	}

}
