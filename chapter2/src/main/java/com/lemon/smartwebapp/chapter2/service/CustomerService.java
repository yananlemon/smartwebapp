package com.lemon.smartwebapp.chapter2.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lemon.smartwebapp.chapter2.entity.Customer;
import com.lemon.smartwebapp.chapter2.util.DBCPUtil;

public class CustomerService {

	public int createCustomer(Customer customer){
		Connection conn 	 = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			conn = DBCPUtil.getConnection();
			String sql = "insert into customer (name,contact,telephone,email,remark) values (?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, customer.getName());
			ps.setString(2, customer.getContact());
			ps.setString(3, customer.getTelephone());
			ps.setString(4, customer.getEmail());
			ps.setString(5, customer.getRemark());
			result =ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBCPUtil.close(null, ps, conn);
		}
		return result;
	}

	public List<Customer> listCustomer() {
		Connection conn 	 = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Customer> result = new ArrayList<Customer>();
		try {
			conn = DBCPUtil.getConnection();
			String sql = "select * from  customer";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				Customer customer = new Customer(
						rs.getInt("id"),
						rs.getString("name"), 
						rs.getString("contact"), 
						rs.getString("telephone"),
						rs.getString("email"),
						rs.getString("remark"));
				result.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBCPUtil.close(rs, ps, conn);
		}
		return result;
	}
	
	public Customer getCustomerById(long id) {
		Connection conn 	 = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Customer customer = null;
		try {
			conn = DBCPUtil.getConnection();
			String sql = "select * from  customer where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				customer = new Customer(
						rs.getInt("id"),
						rs.getString("name"), 
						rs.getString("contact"), 
						rs.getString("telephone"),
						rs.getString("email"),
						rs.getString("remark"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBCPUtil.close(rs, ps, conn);
		}
		return customer;
	}

	public int updateCustomer(Customer customer) {
		Connection conn 	 = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			conn = DBCPUtil.getConnection();
			String sql = "update customer set name = ?,contact = ?,telephone = ?,email = ?,remark= ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, customer.getName());
			ps.setString(2, customer.getContact());
			ps.setString(3, customer.getTelephone());
			ps.setString(4, customer.getEmail());
			ps.setString(5, customer.getRemark());
			result =ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBCPUtil.close(null, ps, conn);
		}
		return result;
	}
	
	public int deleteCustomerById(long id) {
		Connection conn 	 = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			conn = DBCPUtil.getConnection();
			String sql = "delete from customer where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			result =ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBCPUtil.close(null, ps, conn);
		}
		return result;
	}
	
}
