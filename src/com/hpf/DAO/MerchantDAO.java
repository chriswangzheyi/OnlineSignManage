package com.hpf.DAO;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.hpf.model.MerchantModel;

@Component
public interface MerchantDAO {

	public List<Map<String, Object>> detailsForm (MerchantModel merchantModel);
	
	public List<Map<String, Object>> getRestaurantType (MerchantModel merchantModel);
}
