package com.hpf.DAO;

import org.springframework.stereotype.Component;

import com.hpf.model.ChangePasswordModel;
import com.hpf.model.LoginModel;

@Component
public interface ChangePasswordDAO {
	
	public String changePassword(ChangePasswordModel changePasswordModel, LoginModel loginmodel);
	
	public boolean checkOriginalPassword(ChangePasswordModel changePasswordModel, LoginModel loginmodel);

}
