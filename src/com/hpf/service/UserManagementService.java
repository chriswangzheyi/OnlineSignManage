package com.hpf.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.hpf.DAO.UserManagementDAO;
import com.hpf.model.UserManagementModel;

@Component
public class UserManagementService {
	
	@Autowired
	UserManagementDAO userManagementDAO;
	
	@Autowired
	UserManagementModel userManagementModel;
	

	public List<Map<String, Object>>getUserInfo(){
			
				
		return userManagementDAO.UserList(userManagementModel);		
	}
	
	
	public String blockUser(UserManagementModel userManagementModel){
		//成功状态1 未成功状态0
		String returnStatus=userManagementDAO.blockUser(userManagementModel);
		System.out.println("returnStatus="+returnStatus);
				
		return returnStatus;
	}
	
	
	public String deleteUser(UserManagementModel userManagementModel){
		
		
		return null;		
	}
	
}
