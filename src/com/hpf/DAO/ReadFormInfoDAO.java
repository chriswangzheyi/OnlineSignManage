package com.hpf.DAO;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.hpf.model.FormModel;
import com.hpf.model.LoginModel;

@Component
public interface ReadFormInfoDAO {

	public List<Map<String, Object>> ReadFormInfo(FormModel formModel);
	
	public int ReadNumofPages();
	
	public String setExaminer(FormModel formModel,LoginModel loginModel);
	
	public List<Map<String, Object>> ReadFormInfoWithTime(FormModel formModel);
	
}
