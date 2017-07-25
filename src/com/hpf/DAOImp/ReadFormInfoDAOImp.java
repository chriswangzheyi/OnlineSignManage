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
		String sql ="select count(id) from ec_online_sign";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		try {
			double num= jdbcTemplate.queryForObject(sql, int.class);
			double div=10;			
			numOfPages=  (int) Math.ceil(num/div);		
			
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


	@Override
	public List<Map<String, Object>> ReadFormInfoWithParameter(FormModel formModel) {
		
		String sql ="select id, restaurantName, restaurantProvince, "
				+ "restaurantCity,restaurantDistrict,"
				+ "restaurantType,restaurantTel,submitTime,"
				+ "examineStatus,examiner, failReason from ec_online_sign where";
				
				//限制时间
				if(!formModel.getFilterStartTime().equals("")&&formModel.getFilterStartTime()!=null){
					sql +=  " submitTime>'"+formModel.getFilterStartTime()+"' and ";					
				}

				if(!formModel.getFilterEndTime().equals("")&&formModel.getFilterEndTime()!=null){
					sql +=  " submitTime<'"+formModel.getFilterEndTime()+"' and ";					
				}
				
				//限制地区
				if(!formModel.getFilterProvince().equals("-1")){
					sql +=  " restaurantProvince='"+formModel.getFilterProvince()+"' and ";		
				}
				
				if(!formModel.getFilterCity().equals("-1")){
					sql +=  " restaurantCity='"+formModel.getFilterCity()+"' and ";		
				}
				
				if(!formModel.getFilterDistrict().equals("-1")){
					sql +=  " restaurantDistrict='"+formModel.getFilterDistrict()+"' and ";		
				}
				
				
				//审核状态
				if(!formModel.getFilterExaminedStatus().equals("")&&formModel.getFilterExaminedStatus()!=null){
					sql +=  " examineStatus='"+formModel.getFilterExaminedStatus()+"' and ";					
				}
				
				
				//搜索关键字
				if(!formModel.getFilterKeyword().equals("")&&formModel.getFilterKeyword()!=null){
					sql +=  " restaurantName like '%"+formModel.getFilterKeyword()+"%' or ";
					sql +=  " restaurantTel like '%"+formModel.getFilterKeyword()+"%' or ";
					sql +=  " examiner like '% "+formModel.getFilterKeyword()+"%' or ";
					sql +=  " restaurantType like '%"+formModel.getFilterKeyword()+"%' ";
				}
				

				
				
				if(sql.endsWith("and ")){sql=sql.substring(0, sql.length()-4);}
				
				sql += "limit "+ 
				(formModel.getCurrentPage()-1)*10+
				" ,10";		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		
		
			FormInfo=jdbcTemplate.queryForList(sql);
			System.out.println("form="+FormInfo);

			

		
		return FormInfo;
		
	}
	
	
	
	

}
