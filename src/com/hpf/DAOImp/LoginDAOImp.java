package com.hpf.DAOImp;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hpf.DAO.LoginDAO;
import com.hpf.model.LoginModel;


@Repository("LoginDAO")
public class LoginDAOImp implements LoginDAO {
	
	@Autowired
	DataSource DataSource;
	
	
	String password=null;

	@Override
	public String loginValidation(LoginModel loginModel) {
	
		String sql ="select password from DATABASENAME where username="+ loginModel.getUsername();		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(DataSource);
		
		try {
			password = jdbcTemplate.queryForObject(sql, java.lang.String.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return password;
	}

}
