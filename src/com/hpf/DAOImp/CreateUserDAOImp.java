package com.hpf.DAOImp;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import com.hpf.DAO.CreateUserDAO;
import com.hpf.model.CreateUserModel;


@Repository("CreateDAO")
public class CreateUserDAOImp implements CreateUserDAO {
	
	private static Log logger = LogFactory.getLog(CreateUserDAOImp.class.getName());
	
	@Autowired  
	DataSource dataSource;

	@Override
	public void createUser(CreateUserModel createUserModel) {
		
		JdbcTemplate jdbcTemplate= new JdbcTemplate(dataSource);

		String sql = "INSERT INTO DATABASENAME"
				+ "(id, password,phone) VALUES(?,?,?)";
		
		try {
			
			jdbcTemplate.update(sql, createUserModel.getId(), 
					createUserModel.getPassword(),createUserModel.getPhone());
			
		} catch (Exception e) {
			logger.error("网签管理后台新建用户出错:",e);
		}


	}

}
