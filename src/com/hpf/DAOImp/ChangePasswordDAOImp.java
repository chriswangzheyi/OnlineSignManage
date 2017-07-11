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
			return "error";
		}
		
	}

	@Override
	public boolean checkOriginalPassword(ChangePasswordModel changePasswordModel, LoginModel loginmodel) {

		String sql="Select password where username="+ loginmodel.getUsername();
		JdbcTemplate jdbcTemplate= new JdbcTemplate(dataSource);
		
		
		try {

			if(jdbcTemplate.queryForObject(sql, java.lang.String.class).equals(
					changePasswordModel.getOriginalPassword() )){
				return true;
			}else {
				return false;
			}
			
			
		} catch (Exception e) {
			
		}
		
		return false;
	}
	
	

}
