package com.hpf.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hpf.DAO.MerchantDAO;
import com.hpf.model.DetailsModifyModel;
import com.hpf.model.LoginModel;
import com.hpf.model.MerchantModel;
import com.hpf.service.DetailsModifyService;
import com.hpf.util.UUIDGenerator;

@Controller
public class DetailsController {
	
	@Autowired
	MerchantModel MerchantModel;
	
	@Autowired
	MerchantDAO MerchantDAO;
	
	@Autowired
	DetailsModifyModel DetailsModifyModel;
	
	@Autowired
DetailsModifyService DetailsModifyService;
	
	@Autowired
	LoginModel LoginModel;
	
	@RequestMapping(value="/details")
	public String detailPage(HttpServletRequest request){
		
		String id =null;
				
		try {
			//parameter为中文需要转码
			id = new String(request.getParameter("id").getBytes("iso-8859-1"), "utf-8");
			DetailsModifyModel.setId(Integer.parseInt(id));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	
		MerchantModel.setId(Integer.parseInt(id));
		List <Map<String, Object>> merchantList= MerchantDAO.detailsForm(MerchantModel);
				
		MerchantModel.setMerchantInfo(merchantList);		
		request.setAttribute("detailform",merchantList);
		request.setAttribute("authLevel",LoginModel.getAuthLevel());
				
		return "details";
	}
	
	
	@RequestMapping(value="/detailsModify")
	public String detailModifyPage(HttpServletRequest request){

		//餐厅种类
		request.setAttribute("allRestaurantType", MerchantModel.getAllRestaurantType());
		
		//表格信息
		request.setAttribute("detailform", MerchantModel.getMerchantInfo());
		
		//权限信息
		request.setAttribute("authLevel",LoginModel.getAuthLevel());
		
		return "detailsModify";
	}
	
	

    @RequestMapping(value="/updateSubmit")
    public String detailsUpdate(
    		@RequestParam(value = "viewfiles", required = false) MultipartFile[] viewfiles,
    	    @RequestParam(value = "licensefile", required = false) MultipartFile licensefile,
    	    @RequestParam(value = "contractfile", required = false) MultipartFile contractfile,
    	    @RequestParam(value = "attorneyfile", required = false) MultipartFile attorneyfile,
    		HttpServletRequest request,  		
    		@RequestParam("restaurant_name") String restaurantName, 
    		@RequestParam("restaurant_province") String restaurantProvince, 
    		@RequestParam("restaurant_city") String restaurantCity,
    		@RequestParam("restaurant_district") String restaurantDistrict,
    		@RequestParam("restaurant_street") String restaurantStreet,
    		@RequestParam("restaurant_type") String restaurantType,
    		@RequestParam("restaurant_address") String restaurantAddress,
    		@RequestParam("restaurant_tel") String restaurantTel,
    		@RequestParam("restaurant_opentime") String restaurantOpentime,
    		@RequestParam("restaurant_closetime") String restaurantClosetime,
    		@RequestParam("restaurant_indroduction") String restaurantIndroduction,
    		@RequestParam("manager_phone") String managerPhone,   		
    		@RequestParam("boss_phone") String bossPhone,
    		@RequestParam("bankaccount_name") String bankaccountName,
    		@RequestParam("bankaccount_bank") String bankaccountBank,
    		@RequestParam("bankaccount_account") String bankaccountAccount  
    		 		
    		) {
    	
	
    	//截取地址内容
    	restaurantProvince=restaurantProvince.substring(restaurantProvince.lastIndexOf(",")+1);
    	restaurantCity=restaurantCity.substring(restaurantCity.lastIndexOf(",")+1);
    	restaurantDistrict=restaurantDistrict.substring(restaurantDistrict.lastIndexOf(",")+1);
    	restaurantStreet=restaurantStreet.substring(restaurantStreet.lastIndexOf(",")+1);
    	
    	
    	//设置参数
    	DetailsModifyModel.setRestaurantName(restaurantName);
    	DetailsModifyModel.setRestaurantProvince(restaurantProvince);
    	DetailsModifyModel.setRestaurantCity(restaurantCity);
    	DetailsModifyModel.setRestaurantDistrict(restaurantDistrict);
    	DetailsModifyModel.setRestaurantStreet(restaurantStreet);
    	DetailsModifyModel.setRestaurantAddress(restaurantAddress);
    	DetailsModifyModel.setRestaurantType(restaurantType);
    	DetailsModifyModel.setRestaurantTel(restaurantTel);
    	DetailsModifyModel.setRestaurantOpentime(restaurantOpentime);	
    	DetailsModifyModel.setRestaurantClosetime(restaurantClosetime);
    	DetailsModifyModel.setRestaurantIndroduction(restaurantIndroduction);
    	DetailsModifyModel.setManagerPhone(managerPhone);
    	DetailsModifyModel.setBossPhone(bossPhone);
    	DetailsModifyModel.setBankaccountName(bankaccountName);
    	DetailsModifyModel.setBankaccountAccount(bankaccountAccount);
    	DetailsModifyModel.setBankaccountAccount(bankaccountAccount);
    	
    	/*上传文件 */   	
    	//多个文件	
        if(viewfiles!=null && viewfiles.length>0){  
        	String viewfilenames = "";
            for(int i = 0;i<viewfiles.length;i++){  
                MultipartFile file = viewfiles[i];  

                try {
                    //获取存取路径
                    String path = request.getSession().getServletContext().getRealPath("/") + "upload/";
                    String filename=file.getOriginalFilename();         
                    String prefix=filename.substring(filename.lastIndexOf(".")+1);
                    filename=UUIDGenerator.UUIDGenerator()+"."+prefix;
                    
                    File FinalFile = new File(path, filename);
                    if(!FinalFile.exists()){  
                    	FinalFile.mkdirs();  
                    }
                    // 转存文件  
                    file.transferTo(FinalFile); 

                    viewfilenames+=filename+",";
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }  
            }
            DetailsModifyModel.setViewsPath(viewfilenames);
        	}
            
           
            //单个文件     
            String path = request.getSession().getServletContext().getRealPath("/") + "upload/";  
            
            DetailsModifyModel.setBaseurl(path);
            
            String licenseName = licensefile.getOriginalFilename();
            String prefix=licenseName.substring(licenseName.lastIndexOf(".")+1);
            licenseName=UUIDGenerator.UUIDGenerator()+"."+prefix;
            
            String contractName= contractfile.getOriginalFilename();
            prefix=contractName.substring(contractName.lastIndexOf(".")+1);
            contractName=UUIDGenerator.UUIDGenerator()+"."+prefix;
            
            String attorneyName= attorneyfile.getOriginalFilename();
            prefix=attorneyName.substring(attorneyName.lastIndexOf(".")+1);
            attorneyName=UUIDGenerator.UUIDGenerator()+"."+prefix;
            
            
            
            File licenseFinalFile = new File(path, licenseName);
            File contractFinalFile = new File(path, contractName);
            File attorneyFinalFile = new File(path, attorneyName);
            
            if(!licenseFinalFile.exists()){  
                licenseFinalFile.mkdirs();  
            }
            
            if(!contractFinalFile.exists()){  
            	contractFinalFile.mkdirs();  
            }
            
            if(!attorneyFinalFile.exists()){  
            	attorneyFinalFile.mkdirs();  
            }
            
            try { 	
            	 licensefile.transferTo(licenseFinalFile); 
            	 contractfile.transferTo(contractFinalFile);
            	 attorneyfile.transferTo(attorneyFinalFile);
            	 
            	 DetailsModifyModel.setLicensepath(licenseName);
            	 DetailsModifyModel.setContractpath(contractName);
            	 DetailsModifyModel.setAttorneypath(attorneyName);
            	 
            	 
            	} catch (Exception e) {  
            	 e.printStackTrace();
            	}  
                   	  
    	
    	//修改内容详情并添加到数据库	
		return DetailsModifyService.updateDetails();  //返回success 或者fail	
    } 
	
	
	

}
