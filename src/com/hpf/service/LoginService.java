package com.hpf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hpf.DAO.LoginDAO;
import com.hpf.model.LoginModel;

@Component
public class LoginService {
		
	@Autowired
	private LoginDAO loginDAO;
	
	@Autowired
	private LoginModel loginModel;
	
	
	public boolean passowrdValidation(String usernameInput, String passwordInput){
		
		loginModel.setUsername(usernameInput);
	
		//正确的密码
		String passwordExpected = loginDAO.loginValidation(loginModel);
		
		if(passwordExpected.equals(passwordExpected)){return true;}
						
		return false;
	}
		
}
