package com.hpf.DAOImp;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hpf.DAO.MerchantDAO;
import com.hpf.model.MerchantModel;


@Repository("MerchantDAO")
public class MerchantDAOImp implements MerchantDAO {
	
	private static Log logger = LogFactory.getLog(MerchantDAOImp.class.getName());
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	MerchantModel MerchantDAO;
	

	/*读取某一个商户信息，以id为filter*/
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
			logger.error("网签管理后台,在查看商户详情时出错:", e);
		}		
		
		return detailForm;
	}


	/*读取所有餐厅类型*/
	@Override
	public List<Map<String, Object>> getRestaurantType(MerchantModel merchantModel) {
		String sql ="select name from ec_bus_shop_type";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		try {
			List<Map<String, Object>> restaurantTypeList = jdbcTemplate.queryForList(sql); 
					   
			   return restaurantTypeList;
		} catch (Exception e) {
			logger.error("商家网签后台，读取餐厅类型时出错:",e);
			return null;
		}

	}


	

}
