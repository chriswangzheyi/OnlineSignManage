package com.hpf.model;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class DashboardModel {

	List <Map<String, Object>> formInfoList;
	List <Map<String, Object>> userInfoList;
	

	public List<Map<String, Object>> getFormInfoList() {
		return formInfoList;
	}

	public void setFormInfoList(List<Map<String, Object>> formInfoList) {
		this.formInfoList = formInfoList;
	}

	public List<Map<String, Object>> getUserInfoList() {
		return userInfoList;
	}

	public void setUserInfoList(List<Map<String, Object>> userInfoList) {
		this.userInfoList = userInfoList;
	}
	
	
	
}
