package com.lemon.entity;

public class Customer {
	
	private long id;			// 主键
	private String name;		// 姓名
	private String contact;		// 联系人 
	private String telephone;	// 电话
	private String email;		// 邮件
	private String remark;		// 备注
	
	public Customer() {
		super();
	}

	
	
	public Customer(long id, String name, String contact, String telephone,
			String email, String remark) {
		super();
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.telephone = telephone;
		this.email = email;
		this.remark = remark;
	}



	public Customer(String name, String contact, String telephone,
			String email, String remark) {
		super();
		this.name = name;
		this.contact = contact;
		this.telephone = telephone;
		this.email = email;
		this.remark = remark;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
