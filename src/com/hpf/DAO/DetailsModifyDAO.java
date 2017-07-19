package com.hpf.DAO;

import org.springframework.stereotype.Component;

import com.hpf.model.DetailsModifyModel;

@Component
public interface DetailsModifyDAO {
	
	public String updateDetails(DetailsModifyModel detailsModifyModel);

}
