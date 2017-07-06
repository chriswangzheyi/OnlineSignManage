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
	private Date lastLoginTime;
	private String status;
	private String blockid;
	private String deleteid;
	
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getBlockid() {
		return blockid;
	}
	public void setBlockid(String blockid) {
		this.blockid = blockid;
	}
	public String getDeleteid() {
		return deleteid;
	}
	public void setDeleteid(String deleteid) {
		this.deleteid = deleteid;
	}
	
	
	
}
