package com.hpf.model;

import org.springframework.stereotype.Component;

@Component
public class CreateUserModel {
	
	private String id;
	private String password;
	private String phone;
	
	//getter and setter
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	
}
