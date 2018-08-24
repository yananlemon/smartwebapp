package com.lemon.dao;

import com.lemon.entity.Customer;
import com.lemon.smartwebframework.core.annotation.DataAccessLayer;

@DataAccessLayer
public interface ICustomerDao {

	public int insert(Customer obj);
}
