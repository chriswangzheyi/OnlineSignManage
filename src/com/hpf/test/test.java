package com.hpf.test;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class test {

	@RequestMapping(value="/test")
	public ModelAndView testMyMAV(HttpSession httpSession, HttpServletResponse response	
			){
		ModelAndView mav = new ModelAndView("logout");
		mav.addObject("test", "test");
		
		httpSession.setAttribute("testsession", "testsession");
		Cookie c1= new Cookie("testUser", "testUser");
		c1.setMaxAge(100000);
		response.addCookie(c1);		
		return mav;
	}
	
/*	  @Scheduled(cron = "0/5 * * * * *")  
	    public void job1() {
	        System.out.println("任务进行中。。。");  
	    }*/
	
}
