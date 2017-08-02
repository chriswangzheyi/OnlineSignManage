package com.hpf.DAOImp;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hpf.DAO.LoginDAO;
import com.hpf.model.LoginModel;


@Repository("LoginDAO")
public class LoginDAOImp implements LoginDAO {
	
	private static Log logger = LogFactory.getLog(LoginDAOImp.class.getName());
	
	@Autowired
	DataSource DataSource;
	
	List<Map<String, Object>> restaurantTypeList=null;


	@Override
	public List<Map<String, Object>> loginValidation(LoginModel loginModel) {
				
		String sql ="select password, status, authLevel from ec_online_sign_user where username="+ "'"+loginModel.getUsername()+ "'";		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(DataSource);
		
	
		try {
			restaurantTypeList=jdbcTemplate.queryForList(sql);
		} catch (Exception e) {
			logger.error("商家网签后台登录出错:",e);

		}
	
		
		return restaurantTypeList;
	}


	@Override
	public void updateLastLoginTime(LoginModel loginModel) {
		String sql="update ec_online_sign_user set lastLoginTime = ? "
				+ "where username =?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(DataSource);
		
		jdbcTemplate.update(sql,loginModel.getLoginTime(),loginModel.getUsername());
		
	}

}
