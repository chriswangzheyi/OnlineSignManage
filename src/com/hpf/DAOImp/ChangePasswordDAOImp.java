package com.hpf.DAOImp;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hpf.DAO.ChangePasswordDAO;

import com.hpf.model.ChangePasswordModel;
import com.hpf.model.LoginModel;

@Repository("ChangePasswordDAO")
public class ChangePasswordDAOImp implements ChangePasswordDAO {
	
	private static Log logger = LogFactory.getLog(ChangePasswordDAOImp.class.getName());

	@Autowired
	DataSource dataSource;
	
	/*修改密码*/
	@Override
	public String changePassword(ChangePasswordModel changePasswordModel, LoginModel loginModel) {
		
		JdbcTemplate jdbcTemplate= new JdbcTemplate(dataSource);


		//修改密码sql
		String sql = "UPDATE ec_online_sign_user SET password =?"+
				"where username=?";		
		
		try {
			 jdbcTemplate.update(sql, changePasswordModel.getNewPassword(),loginModel.getUsername());
			 
			 return "success";
		} catch (Exception e) {
			logger.error("网签管理后台修改密码错误:",e);
			return "error";
		}
		
	}

	/*原来的密码*/
	@Override
	public boolean checkOriginalPassword(ChangePasswordModel changePasswordModel, LoginModel loginmodel) {

		String sql="Select password from ec_online_sign_user where username= '"+ loginmodel.getUsername()+"'";
		JdbcTemplate jdbcTemplate= new JdbcTemplate(dataSource);
				
		try {

			if(jdbcTemplate.queryForObject(sql, java.lang.String.class).equals(
					changePasswordModel.getOriginalPassword() )){
				return true;
			}else {
				
				return false;
			}
						
		} catch (Exception e) {
			logger.error("网签管理后台检查原始密码出错: ",e);
			
		}		
		return false;
	}
		
}
