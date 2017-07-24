package com.hpf.DAOImp;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hpf.DAO.MerchantDAO;
import com.hpf.model.MerchantModel;

import net.sf.json.JSONArray;

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
				+ "restaurantCity,restaurantDistrict, restaurantStreet, restaurantAddress,"
				+ "restaurantType,restaurantTel,restaurantOpenTime,"
				+ "restaurantCloseTime,restaurantIndroduction,"
				+ "viewURL,managerPhone,bossPhone,baseURL,licenseURL,"
				+ "contractURL,bankAccountName,bankAccountBank,"
				+ "bankAccountAccount,attorneyURL "
				+ "from ec_online_sign where id='"+MerchantDAO.getId()+"'";	
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			
		try {
			detailForm=jdbcTemplate.queryForList(sql);
		} catch (Exception e) {
			// TODO: handle exception
		}		
		
		return detailForm;
	}


	@Override
	public List<Map<String, Object>> getRestaurantType(MerchantModel merchantModel) {
		String sql ="select name from ec_bus_shop_type";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		try {
			List<Map<String, Object>> restaurantTypeList = jdbcTemplate.queryForList(sql); 
					   
			   return restaurantTypeList;
		} catch (Exception e) {
	
		}

		return null;
	}


	@Override
	public List<Map<String, Object>> getRegion(MerchantModel merchantModel) {
		
		String sql ="select id, name, pid,regLevel from ec_sys_region"; 
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		
		try {
			//读取地区信息并存入Json
			List<Map<String, Object>> regionList=jdbcTemplate.queryForList(sql);
	        String regionJson =JSONArray.fromObject(regionList).toString();
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

}
