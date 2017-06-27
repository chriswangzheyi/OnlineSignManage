package com.hpf.DAO;

import org.springframework.stereotype.Component;

import com.hpf.model.LoginModel;

@Component
public interface LoginDAO {
	
	public String loginValidation(LoginModel loginModel);
	
}
