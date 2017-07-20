package com.hpf.model;

import org.springframework.stereotype.Component;

@Component
public class DetailsModifyModel {
	
	//参数
	private String restaurantName;
	private String restaurantProvince;
	private String restaurantCity;
	private String restaurantDistrict;
	private String restaurantStreet;
	private String restaurantType;
	private String restaurantAddress;
	private String restaurantTel;
	private String restaurantOpentime;
	private String restaurantClosetime;
	private String restaurantIndroduction;
	private String managerPhone;
	private String bossPhone;
	private String bankaccountName;
	private String bankaccountBank;
	private String bankaccountAccount;
	private String baseurl;
	private String contractpath;
	private String attorneypath;
	private String licensepath;
	private String viewsPath;
	private int id;


	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	//getter and setter
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getRestaurantProvince() {
		return restaurantProvince;
	}
	public void setRestaurantProvince(String restaurantProvince) {
		this.restaurantProvince = restaurantProvince;
	}
	public String getRestaurantCity() {
		return restaurantCity;
	}
	public void setRestaurantCity(String restaurantCity) {
		this.restaurantCity = restaurantCity;
	}
	public String getRestaurantDistrict() {
		return restaurantDistrict;
	}
	public void setRestaurantDistrict(String restaurantDistrict) {
		this.restaurantDistrict = restaurantDistrict;
	}
	public String getRestaurantStreet() {
		return restaurantStreet;
	}
	public void setRestaurantStreet(String restaurantStreet) {
		this.restaurantStreet = restaurantStreet;
	}
	public String getRestaurantType() {
		return restaurantType;
	}
	public void setRestaurantType(String restaurantType) {
		this.restaurantType = restaurantType;
	}
	public String getRestaurantAddress() {
		return restaurantAddress;
	}
	public void setRestaurantAddress(String restaurantAddress) {
		this.restaurantAddress = restaurantAddress;
	}
	public String getRestaurantTel() {
		return restaurantTel;
	}
	public void setRestaurantTel(String restaurantTel) {
		this.restaurantTel = restaurantTel;
	}
	public String getRestaurantOpentime() {
		return restaurantOpentime;
	}
	public void setRestaurantOpentime(String restaurantOpentime) {
		this.restaurantOpentime = restaurantOpentime;
	}
	public String getRestaurantClosetime() {
		return restaurantClosetime;
	}
	public void setRestaurantClosetime(String restaurantClosetime) {
		this.restaurantClosetime = restaurantClosetime;
	}
	public String getRestaurantIndroduction() {
		return restaurantIndroduction;
	}
	public void setRestaurantIndroduction(String restaurantIndroduction) {
		this.restaurantIndroduction = restaurantIndroduction;
	}
	public String getManagerPhone() {
		return managerPhone;
	}
	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}
	public String getBossPhone() {
		return bossPhone;
	}
	public void setBossPhone(String bossPhone) {
		this.bossPhone = bossPhone;
	}
	public String getBankaccountName() {
		return bankaccountName;
	}
	public void setBankaccountName(String bankaccountName) {
		this.bankaccountName = bankaccountName;
	}
	public String getBankaccountBank() {
		return bankaccountBank;
	}
	public void setBankaccountBank(String bankaccountBank) {
		this.bankaccountBank = bankaccountBank;
	}
	public String getBankaccountAccount() {
		return bankaccountAccount;
	}
	public void setBankaccountAccount(String bankaccountAccount) {
		this.bankaccountAccount = bankaccountAccount;
	}
	public String getBaseurl() {
		return baseurl;
	}
	public void setBaseurl(String baseurl) {
		this.baseurl = baseurl;
	}
	public String getContractpath() {
		return contractpath;
	}
	public void setContractpath(String contractpath) {
		this.contractpath = contractpath;
	}
	public String getAttorneypath() {
		return attorneypath;
	}
	public void setAttorneypath(String attorneypath) {
		this.attorneypath = attorneypath;
	}
	public String getLicensepath() {
		return licensepath;
	}
	public void setLicensepath(String licensepath) {
		this.licensepath = licensepath;
	}
	public String getViewsPath() {
		return viewsPath;
	}
	public void setViewsPath(String viewsPath) {
		this.viewsPath = viewsPath;
	}


}
