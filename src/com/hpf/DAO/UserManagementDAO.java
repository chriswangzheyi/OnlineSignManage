package com.hpf.DAO;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.hpf.model.UserManagementModel;

@Component
public interface UserManagementDAO {

	public List<Map<String, Object>> UserList(UserManagementModel userManagementModel);
		
	public String blockUser(UserManagementModel userManagementModel);
	
	public String unblockUser(UserManagementModel userManagementModel);
	
	public String deleteUser(UserManagementModel userManagementModel);
	
	public String newUser(UserManagementModel userManagementModel);
	
	public boolean isUserExisted(UserManagementModel userManagementModel);
	
	public String getNewPageNum(UserManagementModel userManagementModel);
	
}
