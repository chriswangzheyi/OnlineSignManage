package com.hpf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DetailController {
	
	@RequestMapping(value="detail")
	public String detailPage(){
		
		return null;
	}

}
