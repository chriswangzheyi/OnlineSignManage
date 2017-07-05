package com.hpf.DAO;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.hpf.model.FormModel;

@Component
public interface ReadFormInfoDAO {

	public List<Map<String,Object>> ReadFormInfo(FormModel formModel);
	
}
