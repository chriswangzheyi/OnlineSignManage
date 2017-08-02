package com.hpf.test;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class test5 {


	@Value("${config.imageBaseURL}")  
	 private String imageBaseURL;   
	@RequestMapping(value="/test5")
	public String gettest(HttpServletRequest request
			){
		request.setAttribute("url", imageBaseURL);
		
		return "test";
		
		
	}
	
}
