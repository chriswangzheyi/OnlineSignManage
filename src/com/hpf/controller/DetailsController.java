package com.hpf.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hpf.DAO.MerchantDAO;
import com.hpf.model.DetailsModifyModel;
import com.hpf.model.LoginModel;
import com.hpf.model.MerchantModel;
import com.hpf.service.DetailsModifyService;
import com.hpf.util.DeleteFile;
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
	
	@Value("${config.imageBaseURL}")  
	 private String imageBaseURL;
	
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
		
		//设置原始viewurl
		MerchantModel.setViewURL(merchantList.get(0).get("viewURL").toString());
		MerchantModel.setContractURL(merchantList.get(0).get("contractURL").toString());
		MerchantModel.setAttorneyURL(merchantList.get(0).get("attorneyURL").toString());
		MerchantModel.setLicenseURL(merchantList.get(0).get("attorneyURL").toString());

		//设置表单内容
		MerchantModel.setMerchantInfo(merchantList);		
		request.setAttribute("detailform",merchantList);
		request.setAttribute("authLevel",LoginModel.getAuthLevel());
		request.setAttribute("imageBaseURL", imageBaseURL);
				
		return "details";
	}
	
	

	@RequestMapping(value="/detailsModify")
	public String detailModifyPage(HttpServletRequest request){

		//餐厅种类
		request.setAttribute("allRestaurantType", MerchantModel.getAllRestaurantType());
		
		//表格信息
		request.setAttribute("detailform", MerchantModel.getMerchantInfo());
		request.setAttribute("imageBaseURL", imageBaseURL);
		
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
    		@RequestParam("bankaccount_account") String bankaccountAccount,
    		String deletedViewPicURL //删除的餐厅view图片
    		
    		) {

    	//截取餐厅实拍的内容  如果图片地址变化请修改此处
    	 deletedViewPicURL=deletedViewPicURL.replaceAll(imageBaseURL, "");
    	
    	//截取地址内容
    	restaurantProvince=restaurantProvince.substring(restaurantProvince.lastIndexOf(",")+1);
    	restaurantCity=restaurantCity.substring(restaurantCity.lastIndexOf(",")+1);
    	restaurantDistrict=restaurantDistrict.substring(restaurantDistrict.lastIndexOf(",")+1);
    	restaurantStreet=restaurantStreet.substring(restaurantStreet.lastIndexOf(",")+1);
    	
    	
    	 //获取存取路径
        //String path = request.getSession().getServletContext().getRealPath("/") + "upload/";  //本地测试用
        String path ="/data/hpf/images/onlineSign/";
    	
    	/*上传文件 */   	
    	//多个文件	
        if(viewfiles!=null && viewfiles.length>0){  
      
       		String filename ="";
       		String   modifiedURL =  MerchantModel.getViewURL(); 

        	
        	//取得被删除图片的url
        	if(deletedViewPicURL!=null && !deletedViewPicURL.equals("")){
        		
        		String[]url = deletedViewPicURL.split(",");  
        		
        		for(int i=0;i<url.length;i++){
        		//删除文件
        		DeleteFile.deleteFile(path+url[i]); 		
        		modifiedURL = modifiedURL.replaceAll( url[i]+",","");  
        		}
        	}
            
        	
        	
            for(int i = 0;i<viewfiles.length;i++){  
                MultipartFile file = viewfiles[i];               

                try {
                   
                    filename=file.getOriginalFilename();
                    

                    
                    String prefix=filename.substring(filename.lastIndexOf(".")+1);
                    if(prefix==null || prefix.equals("")){break;}
                    

                    
                    
                    
                    filename=UUIDGenerator.UUIDGenerator()+"."+prefix;
                    
                    File FinalFile = new File(path, filename);
                    if(!FinalFile.exists()){  
                    	FinalFile.mkdirs();  
                    }
                    // 转存文件  
                    file.transferTo(FinalFile); 
                    modifiedURL=modifiedURL+filename+",";   
                } catch (IOException e) {
                    e.printStackTrace();
                }  
            }
            
            DetailsModifyModel.setViewsPath(modifiedURL);
            
        	}
            
           
            //单个文件           
            DetailsModifyModel.setBaseurl(path);
            
            //餐厅执照
            String licenseName = licensefile.getOriginalFilename(); 
            String prefix=licenseName.substring(licenseName.lastIndexOf(".")+1);
            if(prefix!=null && !prefix.equals("")){
            	
	            licenseName=UUIDGenerator.UUIDGenerator()+"."+prefix;
	            File licenseFinalFile = new File(path, licenseName);
	            if(!licenseFinalFile.exists()){  
	                licenseFinalFile.mkdirs();  
	            }
	            
	            try {
	            	licensefile.transferTo(licenseFinalFile);
					DetailsModifyModel.setLicensepath(licenseName);
					 DeleteFile.deleteFile(path+MerchantModel.getLicenseURL());
				} catch (Exception e) {
					e.printStackTrace();
				}   
            }
            
            //餐厅合同
            String contractName= contractfile.getOriginalFilename();
            prefix=contractName.substring(contractName.lastIndexOf(".")+1);
            if(prefix!=null && !prefix.equals("")){
            	
	            contractName=UUIDGenerator.UUIDGenerator()+"."+prefix;
	            File contractFinalFile = new File(path, contractName);
	            
	            if(!contractFinalFile.exists()){  
	            	contractFinalFile.mkdirs();  
	            }
	            
	            try {
	            	contractfile.transferTo(contractFinalFile);
	           	 DetailsModifyModel.setContractpath(contractName);
	           	DeleteFile.deleteFile(path+MerchantModel.getContractURL());
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
            //委托书
            String attorneyName= attorneyfile.getOriginalFilename();
            prefix=attorneyName.substring(attorneyName.lastIndexOf(".")+1);
      
            if(prefix!=null && !prefix.equals("")){     
            	
            attorneyName=UUIDGenerator.UUIDGenerator()+"."+prefix;
            File attorneyFinalFile = new File(path, attorneyName);
            
            if(!attorneyFinalFile.exists()){  
            	attorneyFinalFile.mkdirs();  
            }
            
            try { 	          	 
            	 attorneyfile.transferTo(attorneyFinalFile);
            	 DetailsModifyModel.setAttorneypath(attorneyName);
            	 DeleteFile.deleteFile(path+MerchantModel.getAttorneyURL());
	 
            	} catch (Exception e) {  
            	 e.printStackTrace();
            	}  
            }
            
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
        	DetailsModifyModel.setBankaccountBank(bankaccountBank);
            
            
    	
    	//修改内容详情并添加到数据库	
		DetailsModifyService.updateDetails();  //返回success 或者fail
		 
		 return "updateSubmit";
    } 
	
	
	

}
