package com.lemon.service.impl;

import com.lemon.service.ICustomerService;
import com.lemon.smartwebframework.core.annotation.Service;

@Service
public class CustomerServiceImpl implements ICustomerService{

	private int count;
	
	@Override
	public int increase() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return ++count;
	}

	
}
