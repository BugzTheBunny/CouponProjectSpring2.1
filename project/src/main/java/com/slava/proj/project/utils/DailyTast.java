package com.slava.proj.project.utils;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
public class DailyTast {

	@Scheduled(initialDelay = 2000L, fixedDelay = 2000L)
	void dailyUpdate() {
		// insert daily task here
		System.out.println("Daily task working ");
	}

}
