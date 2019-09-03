package com.mortgage.api.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mortgage.api.service.SchedulerServiceImpl;

@Component
public class TransactionSecheduler {

	@Autowired
	private SchedulerServiceImpl schedulerServiceImpl;
	
	@Scheduled(cron = "0 */1 * * * *")
	public void runTask() {
		schedulerServiceImpl.runTask();
	}
}
