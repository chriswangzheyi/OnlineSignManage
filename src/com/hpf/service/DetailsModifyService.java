package com.hpf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hpf.DAO.DetailsModifyDAO;
import com.hpf.model.DetailsModifyModel;

@Component
public class DetailsModifyService {
	
	@Autowired
	DetailsModifyDAO detailsModifyDAO;
	
	@Autowired
	DetailsModifyModel detailsModifyModel;
	
	public String updateDetails(){
		
		detailsModifyDAO.updateDetails(detailsModifyModel);
				
		return null;
	}

}
