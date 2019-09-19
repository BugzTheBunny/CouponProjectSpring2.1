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

	/*
	 * This is the daily task, it should remove outdated coupons.
	 * 
	 * @if the coupon is expired(by end_date): will change the status to EXPIRED
	 * 
	 * @if the status is EXPIRED: will check the date, if it is outdated - will
	 * remain EXPIRED if the date is fixed, and its not outdated, it will change it
	 * to ONSALE
	 * 
	 * @if the status is REMOVED: will ignore the coupon.
	 * 
	 * @Also counting the amount of updated coupons every run
	 */

	@Autowired
	private CouponRepo cRepo;

	@Scheduled(initialDelay = 5000L, fixedDelay = 5000L)
	void update() {
		long amount = 0;
		LocalDateTime time = LocalDateTime.now();

		List<Coupon> expired = cRepo.findAllByStatusNot(CStatus.ONSALE);
		List<Coupon> outdated = cRepo.findAllByEnddateAfter(Date.valueOf(LocalDate.now()));
		List<Coupon> notExpired = cRepo.findAllByEnddateBefore(Date.valueOf(LocalDate.now()));

		for (Coupon coupon : outdated) {
			if (coupon.getStatus() != CStatus.REMOVED) {
				if (coupon.getStatus() != CStatus.EXPIRED) {
					amount++;
					coupon.setStatus(CStatus.EXPIRED);
					cRepo.save(coupon);

				}
			}
		}

		for (Coupon coupon : notExpired) {
			if (coupon.getStatus() != CStatus.REMOVED) {
				coupon.setStatus(CStatus.ONSALE);
				cRepo.save(coupon);
			}
		}

		/*
		 * Info for the console
		 */
		System.out.println("------- Number of updated coupons today: " + amount);
		System.out.println("Expired Coupons: \n" + expired);
		System.out.println("Outdated Coupons: \n" + outdated);
		System.out.println("Daily task working " + time);
	}

}
