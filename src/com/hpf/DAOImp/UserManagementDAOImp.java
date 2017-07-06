package com.hpf.DAOImp;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hpf.DAO.UserManagementDAO;
import com.hpf.model.UserManagementModel;

@Repository("userManagementDAO")
public class UserManagementDAOImp implements UserManagementDAO {

	@Autowired
	DataSource dataSource;
	
	@Override
	public List<Map<String, Object>> UserList(UserManagementModel userManagementModel) {
		
		String sql="select id, username, phone, createTime, lastLoginTime,status from ec_online_sign_user";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> userList =jdbcTemplate.queryForList(sql);
		
		
		return userList;
	}

	@Override
	public String blockUser(UserManagementModel userManagementModel) {
		String sql="update ec_online_sign_user set status = 0 "
				+ "where id =?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		try {
			jdbcTemplate.update(sql, userManagementModel.getBlockid());
			return "1";
		} catch (Exception e) {
			return "0";
		}
	}

	@Override
	public String deleteUser(UserManagementModel userManagementModel) {
		return null;
	}

}
