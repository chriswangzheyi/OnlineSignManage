package com.hpf.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	
	
	public String passowrdValidation(String usernameInput, String passwordInput){
		
		loginModel.setUsername(usernameInput);
		
		String passwordInDB=null; 
		String statusInDB=null;  //0为冻结 其他值为未冻结
		String authLevel=null;  //2为超级用户 0为普通用户	
	
			
		//取信息
		List<Map<String, Object>> LoginInfo = loginDAO.loginValidation(loginModel);
		if(LoginInfo.size()==0){return "-3";}
		
		
		passwordInDB=(String) LoginInfo.get(0).get("password");
		statusInDB=(String) LoginInfo.get(0).get("status");
		authLevel=(String) LoginInfo.get(0).get("authLevel");
		
				
		if(!passwordInDB.equals(passwordInput)){	
			return "-1";
		}
		
		if(statusInDB.equals("0")){
			return "-2";
		}
							
		
		return authLevel;
	}
		
}
