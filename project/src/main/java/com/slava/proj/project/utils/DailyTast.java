package com.slava.proj.project.utils;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.slava.proj.project.repo.CouponRepo;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
public class DailyTast {

	@Autowired
	private CouponRepo cRepo;

	@Scheduled(initialDelay = 2000L, fixedDelay = 2000L)

	void dailyUpdate() {
		// insert daily task here
		//
		//
		System.out.println(cRepo.findAll().toString());
		LocalDateTime time = LocalDateTime.now();
		System.out.println("Daily task working " + time);
	}

}
