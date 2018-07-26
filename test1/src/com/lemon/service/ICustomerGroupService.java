package com.lemon.service;

import com.lemon.entity.CustomerGroup;

public interface ICustomerGroupService {

	public void addGroup(CustomerGroup group);
	
	public CustomerGroup getById(long id);
}
