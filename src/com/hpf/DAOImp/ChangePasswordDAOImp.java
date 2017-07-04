package com.hpf.DAOImp;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hpf.DAO.ChangePasswordDAO;
import com.hpf.model.ChangePasswordModel;
import com.hpf.model.LoginModel;

@Repository("ChangePasswordDAO")
public class ChangePasswordDAOImp implements ChangePasswordDAO {

	@Autowired
	DataSource dataSource;
	
	@Override
	public String changePassword(ChangePasswordModel changePasswordModel, LoginModel loginModel) {
		
		JdbcTemplate jdbcTemplate= new JdbcTemplate(dataSource);


		//修改密码
		String sql = "UPDATE DATABASENAME SET password =?"+
				"where username=?";		
		try {
			 jdbcTemplate.update(
					sql, changePasswordModel.getNewPassword(),loginModel.getUsername());
			 return "success";
		} catch (Exception e) {
			return "fail";
		}
		
	}

	@Override
	public String checkOriginalPassword(ChangePasswordModel changePasswordModel, LoginModel loginmodel) {

		String passwordInDB = null;
		String sql="Select password where username="+ loginmodel.getUsername();
		JdbcTemplate jdbcTemplate= new JdbcTemplate(dataSource);
		
		
		try {
			passwordInDB=jdbcTemplate.queryForObject(sql, java.lang.String.class);
		} catch (Exception e) {
			
		}
		
		return passwordInDB;
	}
	
	

}
