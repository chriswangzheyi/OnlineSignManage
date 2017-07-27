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
import com.hpf.util.WriteDocument;

import net.sf.json.JSONArray;

@Repository("ReadFormInfoDAO")
public class ReadFormInfoDAOImp implements ReadFormInfoDAO {
	
	@Autowired
	DataSource dataSource;
	
	List<Map<String, Object>> FormInfo;
	List<FormModel> FormModelinfo;
	

	//读取表单信息
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

	//读取总页数
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

	//设置审核信息
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

	//读取搜索后信息
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
				if(!formModel.getFilterExaminedStatus().equals("-1")){
					sql +=  " examineStatus='"+formModel.getFilterExaminedStatus()+"' and ";					
				}
				
				
				//搜索关键字
				if(!formModel.getFilterKeyword().equals("")&&formModel.getFilterKeyword()!=null){
					sql +=  " ( restaurantName like '%"+formModel.getFilterKeyword()+"%' or ";
					sql +=  " restaurantTel like '%"+formModel.getFilterKeyword()+"%' or ";
					sql +=  " examiner like '% "+formModel.getFilterKeyword()+"%' or ";
					sql +=  " restaurantType like '%"+formModel.getFilterKeyword()+"%' ) ";
				}
				
			
				
				if(sql.endsWith("and ")){sql=sql.substring(0, sql.length()-4);}
				if(sql.endsWith("where")){sql=sql.substring(0, sql.length()-5);}
				
				sql += "limit "+ 
				(formModel.getCurrentPage()-1)*10+
				" ,10";		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		
		
			FormInfo=jdbcTemplate.queryForList(sql);
			System.out.println("form="+FormInfo);
		
		return FormInfo;
		
	}

	//读取搜索后总页数
	@Override
	public int ReadNumOfPageWithParameter(FormModel formModel) {
		int numOfPages=0;
		String sql ="select count(id) from ec_online_sign where";
				
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
				if(!formModel.getFilterExaminedStatus().equals("-1")){
					sql +=  " examineStatus='"+formModel.getFilterExaminedStatus()+"' and ";					
				}
				
				
				//搜索关键字
				if(!formModel.getFilterKeyword().equals("")&&formModel.getFilterKeyword()!=null){
					sql +=  " (restaurantName like '%"+formModel.getFilterKeyword()+"%' or ";
					sql +=  " restaurantTel like '%"+formModel.getFilterKeyword()+"%' or ";
					sql +=  " examiner like '% "+formModel.getFilterKeyword()+"%' or ";
					sql +=  " restaurantType like '%"+formModel.getFilterKeyword()+"%' ) ";
				}
				
				
				if(sql.endsWith("and ")){sql=sql.substring(0, sql.length()-4);}
				if(sql.endsWith("where")){sql=sql.substring(0, sql.length()-5);}
							
				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				
				try {
					double num= jdbcTemplate.queryForObject(sql, int.class);
					double div=10;			
					numOfPages=  (int) Math.ceil(num/div);	
					return numOfPages;
					
				} catch (Exception e) {
					System.out.println("出错了");
						return -1;
				}
		
	}

	@Override
	public String updateRegion(String currentPath) {
		
		String sql ="select id, name, pid,regLevel from ec_sys_region";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		try {
			//读取地区信息并存入Json
			List<Map<String, Object>> regionList=jdbcTemplate.queryForList(sql);
	        String regionJson =JSONArray.fromObject(regionList).toString();
	        WriteDocument.writeByBufferedReader(currentPath+"\\"+"cityJson.json",regionJson);
	        return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	
	
	}
	
	
	
	
	

}
