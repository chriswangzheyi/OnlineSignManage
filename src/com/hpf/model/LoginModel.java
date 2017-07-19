package com.hpf.model;

import org.springframework.stereotype.Component;

@Component
public class LoginModel {
	
	private String username;
	private String password;
	private String loginTime;
	private String authLevel;
	
	
	//getter and setter
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
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getAuthLevel() {
		return authLevel;
	}
	public void setAuthLevel(String authLevel) {
		this.authLevel = authLevel;
	}
	
	
	
}
