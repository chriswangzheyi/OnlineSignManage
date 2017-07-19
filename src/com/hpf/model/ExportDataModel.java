package com.hpf.model;

import java.util.Date;

import org.springframework.stereotype.Component;



@Component
public class ExportDataModel {

	private String restaurantName;
	private String restaurantLocation;
	private String restaurantType;	
	private String restaurantTel;
	private Date submitTime;
	private String status;
	private String examiner;
	
	
	//getter and setter
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

	public String getRestaurantTel() {
		return restaurantTel;
	}

	public void setRestaurantTel(String restaurantTel) {
		this.restaurantTel = restaurantTel;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getExaminer() {
		return examiner;
	}

	public void setExaminer(String examiner) {
		this.examiner = examiner;
	}

}
