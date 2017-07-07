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
		String sql="delete from ec_online_sign_user set where id=? ";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		try {
			jdbcTemplate.update(sql, userManagementModel.getDeleteid());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}

	@Override
	public String newUser(UserManagementModel userManagementModel) {
		String sql="insert into ec_online_sign_user(username,password,phone)values(?,?,?) ";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		try {
			jdbcTemplate.update(sql, userManagementModel.getNewAccountUsername(),
					userManagementModel.getNewAccountPassword(),userManagementModel.getNewAccountPhone());
		} catch (Exception e) {
				
		}
		
		return null;
	}

	@Override
	public boolean isUserExisted(UserManagementModel userManagementModel) {
		int idInDB;
		String sql="select id from ec_online_sign_user where username=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		try {
			
			idInDB=jdbcTemplate.update(sql,userManagementModel.getNewAccountUsername());
			
			if ( (Integer)idInDB!=null){return true;}
			else {
				return false;
			}
			
		} catch (Exception e) {
			
		}
		
		
		return false;
	}

}