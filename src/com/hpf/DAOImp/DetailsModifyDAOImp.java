package com.hpf.DAOImp;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hpf.DAO.DetailsModifyDAO;
import com.hpf.model.DetailsModifyModel;

@Repository("DetailsModifyDAO")
public class DetailsModifyDAOImp implements DetailsModifyDAO {
	

	@Autowired
	DataSource dataSource;
	
	@Autowired
	DetailsModifyModel DetailsModifyModel;

	@Override
	public String updateDetails(DetailsModifyModel detailsModifyModel) {
		
		
		JdbcTemplate jdbcTemplate= new JdbcTemplate(dataSource);
		
		String sql = "UPDATE ec_online_sign SET restaurantName =?,restaurantProvince=?, restaurantCity=?,"
				+ "restaurantDistrict=?,restaurantStreet=?,restaurantType=?,restaurantAddress=?,"
				+ "restaurantTel=?,restaurantOpenTime=?,restaurantCloseTime=?,restaurantIndroduction=?,"
				+ "managerPhone=?,bossPhone=?,bankAccountName=?,bankAccountBank=?,"
				+ "bankAccountAccount=?"+
				"where id=?";		
	
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
	
		
		return null;
	}

}
