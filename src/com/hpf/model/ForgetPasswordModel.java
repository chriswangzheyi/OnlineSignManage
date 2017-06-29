package com.hpf.model;

import org.springframework.stereotype.Component;

@Component
public class ForgetPasswordModel {

	private String originalPassword;
	private String newPassword;
	private String confirmPassword;
	
	//getter and setter
	public String getOriginalPassword() {
		return originalPassword;
	}
	public void setOriginalPassword(String originalPassword) {
		this.originalPassword = originalPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	
	
}
