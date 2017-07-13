package com.hpf.DAOImp;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hpf.DAO.MerchantDAO;
import com.hpf.model.MerchantModel;

@Repository("MerchantDAO")
public class MerchantDAOImp implements MerchantDAO {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	MerchantModel MerchantDAO;
	

	@Override
	public List<Map<String, Object>> detailsForm(MerchantModel merchantModel) {
		List<Map<String, Object>> detailForm = null;
		
		String sql ="select restaurantName, restaurantProvince, "
				+ "restaurantCity,restaurantDistrict, restaurantAddress,"
				+ "restaurantType,restaurantTel,restaurantOpenTime,"
				+ "restaurantCloseTime,restaurantIndroduction,"
				+ "viewURL,managerPhone,bossPhone,licenseURL,"
				+ "contractURL,bankAccountName,bankAccountBank,"
				+ "bankAccountAccount,attorneyURL "
				+ "from ec_online_sign where restaurantName='"+MerchantDAO.getRestaurantName()+"'";	
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			
		try {
			detailForm=jdbcTemplate.queryForList(sql);
		} catch (Exception e) {
			// TODO: handle exception
		}		
		
		return detailForm;
	}

}
