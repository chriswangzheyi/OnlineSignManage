package com.hpf.DAOImp;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hpf.DAO.DetailsModifyDAO;
import com.hpf.model.DetailsModifyModel;

@Repository("DetailsModifyDAO")
public class DetailsModifyDAOImp implements DetailsModifyDAO {
	
	private static Log logger = LogFactory.getLog(DetailsModifyDAOImp.class.getName());
	

	@Autowired
	DataSource dataSource;
	
	@Autowired
	DetailsModifyModel DetailsModifyModel;

	/*更新商户信息*/
	@Override
	public String updateDetails(DetailsModifyModel detailsModifyModel) {
		
		
		JdbcTemplate jdbcTemplate= new JdbcTemplate(dataSource);
		
		String sql = "UPDATE ec_online_sign SET restaurantName =?,restaurantProvince=?, restaurantCity=?,"
				+ "restaurantDistrict=?,restaurantStreet=?,restaurantType=?,restaurantAddress=?,"
				+ "restaurantTel=?,restaurantOpenTime=?,restaurantCloseTime=?,restaurantIndroduction=?,"
				+ "managerPhone=?,bossPhone=?,bankAccountName=?,bankAccountBank=?,"
				+ "bankAccountAccount=? ";
				
				if(DetailsModifyModel.getViewsPath()!=null){
				sql+=", viewURL='"+DetailsModifyModel.getViewsPath()+"'";	
				}
				
				if(DetailsModifyModel.getLicensepath()!=null){
				sql+=", licenseURL='"+DetailsModifyModel.getLicensepath()+"'";	
				}
				
				if(DetailsModifyModel.getContractpath()!=null){
				sql+=", contractURL='"+DetailsModifyModel.getContractpath()+"'";	
				}
				
				if(DetailsModifyModel.getAttorneypath()!=null){
				sql+=", attorneyURL='"+DetailsModifyModel.getAttorneypath()+"'";	
				}
	
				sql+="where id=?";		
	
		try {
			jdbcTemplate.update(sql,DetailsModifyModel.getRestaurantName(),DetailsModifyModel.getRestaurantProvince(),
					DetailsModifyModel.getRestaurantCity(),DetailsModifyModel.getRestaurantDistrict(),
					DetailsModifyModel.getRestaurantStreet(),
					DetailsModifyModel.getRestaurantType(),DetailsModifyModel.getRestaurantAddress(),
					DetailsModifyModel.getRestaurantTel(),DetailsModifyModel.getRestaurantOpentime(),
					DetailsModifyModel.getRestaurantClosetime(),DetailsModifyModel.getRestaurantIndroduction(),
					DetailsModifyModel.getManagerPhone(),DetailsModifyModel.getBossPhone(),
					DetailsModifyModel.getBankaccountName(),DetailsModifyModel.getBankaccountBank(),
					DetailsModifyModel.getBankaccountAccount(),
					
					DetailsModifyModel.getId());
			return "success";
			
		} catch (Exception e) {
			logger.error("网签管理后台更新商户信息时出错: ",e);
			return "fail";
		}
	}

}
