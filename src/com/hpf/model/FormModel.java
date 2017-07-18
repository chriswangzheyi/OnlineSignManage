package com.hpf.model;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.hpf.ExcelUtil.Title;

@Component
public class FormModel {
	private List<Map<String, Object>> FormList;
	
	private int id;
	private String restaurantName;
	private String restaurantLocation;
	private String restaurantType;
	private String restaurantPhone;
	private String submitTime;
	private int examineStatus;
	private String examiner;
	private String operation;
	private int currentPage;
	private int totalPage;
	private String examinedExaminer;
	private int examinedRestaurantId;
	private String failReason;
	private int examinedStatus;
	

	//getter and setter	
	public List<Map<String, Object>> getFormList() {
		return FormList;
	}
	public void setFormList(List<Map<String, Object>> formList) {
		FormList = formList;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getRestaurantLocation() {
		return restaurantLocation;
	}
	public void setRestaurantLocation(String restaurantLocation) {
		this.restaurantLocation = restaurantLocation;
	}
	public String getRestaurantType() {
		return restaurantType;
	}
	public void setRestaurantType(String restaurantType) {
		this.restaurantType = restaurantType;
	}
	public String getRestaurantPhone() {
		return restaurantPhone;
	}
	public void setRestaurantPhone(String restaurantPhone) {
		this.restaurantPhone = restaurantPhone;
	}
	public String getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}
	public int getExamineStatus() {
		return examineStatus;
	}
	public void setExamineStatus(int examineStatus) {
		this.examineStatus = examineStatus;
	}
	public String getExaminer() {
		return examiner;
	}
	public void setExaminer(String examiner) {
		this.examiner = examiner;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public String getExaminedExaminer() {
		return examinedExaminer;
	}
	public void setExaminedExaminer(String examinedExaminer) {
		this.examinedExaminer = examinedExaminer;
	}
	public int getExaminedRestaurantId() {
		return examinedRestaurantId;
	}
	public void setExaminedRestaurantId(int examinedRestaurantId) {
		this.examinedRestaurantId = examinedRestaurantId;
	}
	public String getFailReason() {
		return failReason;
	}
	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}
	public int getExaminedStatus() {
		return examinedStatus;
	}
	public void setExaminedStatus(int examinedStatus) {
		this.examinedStatus = examinedStatus;
	}
	
	

	
}
