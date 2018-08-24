package com.lemon.service.impl;

import com.lemon.dao.ICustomerDao;
import com.lemon.entity.Customer;
import com.lemon.service.ICustomerService;
import com.lemon.smartwebframework.core.annotation.Inject;
import com.lemon.smartwebframework.core.annotation.Service;

@Service
public class CustomerServiceImpl implements ICustomerService{

	@Inject
	private ICustomerDao dao;
	
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

	@Override
	public int insert(Customer obj) {
		int rs = 0;
		try{
			rs = dao.insert(obj);
		}catch(Exception e){
			e.printStackTrace();
		}
		return rs;
	}

	
}
