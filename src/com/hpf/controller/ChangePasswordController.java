package com.hpf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChangePasswordController {

	@RequestMapping(value="/changePassword")
	public String forgetPassword(){
		
		return "changePassword";
	}
	
	
}
