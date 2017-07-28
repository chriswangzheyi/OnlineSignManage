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
		//获取用户信息
		String sql="select id, username, phone, createTime, lastLoginTime,status"
				+ " from ec_online_sign_user limit "+(userManagementModel.getCurrentPage()-1)*10+" ,10";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> userList =jdbcTemplate.queryForList(sql);
		
		//获取用户信息页总页数
		String sqlForTotalPage=" select count(id) from ec_online_sign_user";
		
		double num= jdbcTemplate.queryForObject(sqlForTotalPage, int.class);
		double div=10;			
		int numOfPages=  (int) Math.ceil(num/div);		
		userManagementModel.setTotalPageNum(numOfPages);
				
		return userList;
	}

	@Override
	public String blockUser(UserManagementModel userManagementModel) {
		String sql="update ec_online_sign_user set status = 0 "
				+ "where id =?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		try {
			jdbcTemplate.update(sql, userManagementModel.getBlockid());
			return "success";
		} catch (Exception e) {
			return "fail";
		}
	}

	@Override
	public String deleteUser(UserManagementModel userManagementModel) {
		String sql="delete from ec_online_sign_user where id=? ";
		sql +="limit "+(userManagementModel.getCurrentPage()-1)*10+" ,10";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		try {
			jdbcTemplate.update(sql, userManagementModel.getDeleteid());
			return "success";
		} catch (Exception e) {
			return "fail";

		}
		
	
	}

	@Override
	public String newUser(UserManagementModel userManagementModel) {
		String sql="insert into ec_online_sign_user(username,password,phone,createTime)values(?,?,?,?) ";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		try {
			jdbcTemplate.update(sql, userManagementModel.getNewAccountUsername(),
					userManagementModel.getNewAccountPassword(),userManagementModel.getNewAccountPhone(),
					userManagementModel.getNewAccountCreateTime());
			return "success";
		} catch (Exception e) {
			return "fail";
		}
		
	}

	@Override
	public boolean isUserExisted(UserManagementModel userManagementModel) {
		
		String sql="select id from ec_online_sign_user where username="+ userManagementModel.getNewAccountUsername();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				
			
	try {
		if((Integer)jdbcTemplate.queryForObject(sql, java.lang.Integer.class)!=null){ 
		return true;}
		else {
			return false;
		}
	} catch (Exception e) {
		return false;
	}
			
	}

	@Override
	public String unblockUser(UserManagementModel userManagementModel) {
		String sql="update ec_online_sign_user set status = 1 "
				+ "where id =?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		try {
			jdbcTemplate.update(sql, userManagementModel.getUnBlockId());
			return "success";
		} catch (Exception e) {
			return "fail";
		}
	}

	@Override
	public String getNewPageNum(UserManagementModel userManagementModel) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//获取用户信息页x新的总页数
				String sqlForTotalPage=" select count(id) from ec_online_sign_user";
							
				double num= jdbcTemplate.queryForObject(sqlForTotalPage, int.class);
				double div=10;			
				int numOfPages=  (int) Math.ceil(num/div);		
				
				return numOfPages+"";
	}

	

}
