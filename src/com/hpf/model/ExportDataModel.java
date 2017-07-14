package com.hpf.model;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.hpf.ExcelUtil.Title;

@Component
public class ExportDataModel {

	@Title("餐厅名称")
	private String restaurantName;

	@Title("所在地区")
	private String restaurantLocation;
	
	@Title("餐厅类别")
	private String restaurantType;	
	
	@Title("餐厅电话")
	private String restaurantTel;
	
	@Title("提交时间")
	private Date submitTime;
	
	@Title("状态")
	private String status;
	
	@Title("审核")
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
