package com.hpf.DAOImp;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hpf.DAO.ReadFormInfoDAO;
import com.hpf.model.FormModel;
import com.hpf.model.LoginModel;

@Repository("ReadFormInfoDAO")
public class ReadFormInfoDAOImp implements ReadFormInfoDAO {
	
	@Autowired
	DataSource dataSource;
	
	List<Map<String, Object>> FormInfo;
	List<FormModel> FormModelinfo;
	

	
	@Override
	public List<Map<String, Object>> ReadFormInfo(FormModel formModel) {
		
		String sql ="select id, restaurantName, restaurantProvince, "
				+ "restaurantCity,restaurantDistrict,"
				+ "restaurantType,restaurantTel,submitTime,"
				+ "examineStatus,examiner, failReason from ec_online_sign limit "+ 
				(formModel.getCurrentPage()-1)*10+
				" ,10";		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		
		try {
			FormInfo=jdbcTemplate.queryForList(sql);

			
		} catch (Exception e) {
			
		}
		
		return FormInfo;
	}


	@Override
	public int ReadNumofPages() {
		int numOfPages=0;
		String sql ="select count(restaurantName) from ec_online_sign";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		try {
			numOfPages=(int) Math.ceil(jdbcTemplate.queryForObject(sql, int.class)/10);
			
		} catch (Exception e) {
	
		}
		
		return numOfPages;
	}


	@Override
	public String setExaminer(FormModel formModel, LoginModel loginModel) {
		
		String sql = "UPDATE ec_online_sign SET examiner =?, examineStatus=?, failReason=?"+
				"where id=?";	
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		try {
			jdbcTemplate.update(sql,loginModel.getUsername(),formModel.getExaminedStatus(),formModel.getFailReason(),
		    formModel.getExaminedRestaurantId());	
			return "success";
		} catch (Exception e) {
	
			return "fail";
		}

	}
	
	
	
	

}
