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
	String status=null;

	@Override
	public String loginValidation(LoginModel loginModel) {
	
		String sqlForPassword ="select password from DATABASENAME where username="+ loginModel.getUsername();		
		String sqlForAccountStatus="select status from DATABASENAME where username="+ loginModel.getUsername();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(DataSource);
		
		try {
			password = jdbcTemplate.queryForObject(sqlForPassword, java.lang.String.class);
			status = jdbcTemplate.queryForObject(sqlForAccountStatus, java.lang.String.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//如果账户冻结
		if ( !status.equals("0") ){return "-1";}
		
		//返回密码
		return password;
	}

}
