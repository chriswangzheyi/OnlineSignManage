package com.hpf.DAO;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.hpf.model.LoginModel;

@Component
public interface LoginDAO {
	
	public List<Map<String, Object>> loginValidation(LoginModel loginModel);
	
	public void updateLastLoginTime (LoginModel loginModel);
	
}
