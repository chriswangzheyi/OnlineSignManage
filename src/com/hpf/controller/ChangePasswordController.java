package com.hpf.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hpf.model.ChangePasswordModel;
import com.hpf.model.LoginModel;
import com.hpf.service.ChangePasswordService;

@Controller
public class ChangePasswordController {
	
	@Autowired
	ChangePasswordService changePasswordService;	
	
	@Autowired
	ChangePasswordModel changePasswordModel;
	
	@Autowired
	LoginModel LoginModel;

	@RequestMapping(value="/changePassword")
	public String forgetPassword(HttpServletRequest request){
		request.setAttribute("authLevel",LoginModel.getAuthLevel());
		
		return "changePassword";
	}
	
	
	
	@RequestMapping(value="/changePasswordSubmit")
	public String changePasswordsubmit(
			@RequestParam("originalPwdTyped") String originalPwdTyped,
			@RequestParam("newPwdTyped") String newPwdTyped,
			@RequestParam("newPwdConfirmed") String newPwdConfirmed,
			HttpServletRequest request
			){

		changePasswordModel.setOriginalPassword(originalPwdTyped);
		changePasswordModel.setNewPassword(newPwdTyped);
				
		
		String returnMsg =changePasswordService.changePassword(changePasswordModel);
		request.setAttribute("statusMsg", returnMsg);
		
		return "changePassword";
	}
	
}
