package com.hpf.DAOImp;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hpf.DAO.UserManagementDAO;
import com.hpf.model.UserManagementModel;

@Repository("userManagementDAO")
public class UserManagementDAOImp implements UserManagementDAO {
	
	private static Log logger = LogFactory.getLog(UserManagementDAOImp.class.getName());

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

	/*冻结用户*/
	@Override
	public String blockUser(UserManagementModel userManagementModel) {
		String sql="update ec_online_sign_user set status = 0 "
				+ "where id =?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		try {
			jdbcTemplate.update(sql, userManagementModel.getBlockid());
			return "success";
		} catch (Exception e) {
			logger.error("商家网签后台，冻结用户时候出错:",e);
			return "fail";
		}
	}

	/*删除用户*/
	@Override
	public String deleteUser(UserManagementModel userManagementModel) {
		String sql="delete from ec_online_sign_user where id=? ";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		try {
			jdbcTemplate.update(sql, userManagementModel.getDeleteid());
			return "success";
		} catch (Exception e) {
			logger.error("商家网签后台，删除用户时候出错:",e);
			return "fail";

		}
		
	
	}

	/*新增用户*/
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
			logger.error("商家网签后台，新增用户时候出错:",e);
			return "fail";
		}
		
	}

	/*检查是否用户已经存在*/
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
		logger.error("商家网签后台，检查用户是否存在时候出错:",e);
		return false;
	}
			
	}

	/*解冻用户*/
	@Override
	public String unblockUser(UserManagementModel userManagementModel) {
		String sql="update ec_online_sign_user set status = 1 "
				+ "where id =?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		try {
			jdbcTemplate.update(sql, userManagementModel.getUnBlockId());
			return "success";
		} catch (Exception e) {
			logger.error("商家网签后台，解冻用户时出错:",e);
			return "fail";
		}
	}
	
	/*更新最新页面*/
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
