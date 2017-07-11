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
	

	//读取用户信息
	public List<Map<String, Object>>getUserInfo(){
			
				
		return userManagementDAO.UserList(userManagementModel);		
	}
	
	
	//冻结账户
	public String blockUser(UserManagementModel userManagementModel){
		//成功状态1 未成功状态0
		String returnStatus=userManagementDAO.blockUser(userManagementModel);
				
		return returnStatus;
	}
	
	
	//删除账户
	public String deleteUser(UserManagementModel userManagementModel){
		
		//成功状态1 未成功状态0
		String returnStatus=userManagementDAO.deleteUser(userManagementModel);
		
		
		return returnStatus;		
	}
	
	//新建账户
	public String newUser(UserManagementModel userManagementModel){
		//成功状态1 未成功状态0
		String returnStatus= userManagementDAO.newUser(userManagementModel);
		
		
		return returnStatus;
	}
	
	//确认用户是否已经存在
	public boolean isUserExisted(UserManagementModel userManagementModel){
				
		
		return userManagementDAO.isUserExisted(userManagementModel);
	}
}
