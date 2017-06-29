package com.hpf.model;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class UserManagementModel {

	private String id;
	private String username;
	private String password;
	private String phone;
	private Date creatTime;
	private Date LastLoginTime;
	private String status;
	
	//setter and getter
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public Date getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	public Date getLastLoginTime() {
		return LastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		LastLoginTime = lastLoginTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
