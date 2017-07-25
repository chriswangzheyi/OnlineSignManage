package com.hpf.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;


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
	private String filterStartTime;
	private String filterEndTime;
	private String filterKeyword;
	private String filterProvince;
	private String filterCity;
	private String filterDistrict;
	private String filterExaminedStatus;
	

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
	
	public String getFilterStartTime() {
		return filterStartTime;
	}
	public void setFilterStartTime(String filterStartTime) {
		this.filterStartTime = filterStartTime;
	}
	public String getFilterEndTime() {
		return filterEndTime;
	}
	public void setFilterEndTime(String filterEndTime) {
		this.filterEndTime = filterEndTime;
	}
	public String getFilterKeyword() {
		return filterKeyword;
	}
	public void setFilterKeyword(String filterKeyword) {
		this.filterKeyword = filterKeyword;
	}
	public String getFilterProvince() {
		return filterProvince;
	}
	public void setFilterProvince(String filterProvince) {
		this.filterProvince = filterProvince;
	}
	public String getFilterCity() {
		return filterCity;
	}
	public void setFilterCity(String filterCity) {
		this.filterCity = filterCity;
	}
	public String getFilterDistrict() {
		return filterDistrict;
	}
	public void setFilterDistrict(String filterDistrict) {
		this.filterDistrict = filterDistrict;
	}
	public String getFilterExaminedStatus() {
		return filterExaminedStatus;
	}
	public void setFilterExaminedStatus(String filterExaminedStatus) {
		this.filterExaminedStatus = filterExaminedStatus;
	}

}
