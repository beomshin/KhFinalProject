package com.kh.fp.common.controller.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kh.fp.common.service.scheduler.CouponSchedulerService;

@Component
public class CouponSchedulerController {
	
	@Autowired
	private CouponSchedulerService service;
	
	@Scheduled(fixedDelay=10000)
	public void couponDateCheck() {
		service.checkCouponDate();
	}

}