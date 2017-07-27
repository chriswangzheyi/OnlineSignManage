package com.hpf.test;


import javax.servlet.http.HttpSession;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class test2 {

	@RequestMapping(value="/test2")
	public ModelAndView testMyMAV2(HttpSession httpSession,
			@CookieValue("testUser" ) String cookie1){
		ModelAndView mav = new ModelAndView("logout");
		mav.addObject("test", "test2");
		
		String testval=(String) httpSession.getAttribute("testsession");
		System.out.println("test="+testval);
		
		
		System.out.println("cookie的值是="+cookie1);
		
		return mav;
		
	}
}
