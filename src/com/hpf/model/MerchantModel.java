package com.hpf.model;

import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;

@Component
public class MerchantModel {
	
	private List<Map<String, Object>> merchantInfo;
	private List<Map<String, Object>> allRestaurantType;
	private String restaurantName;
	private String restaurantType;
	private String restaurantProvince;
	private String restaurantCity;
	private String restaurantDistrict;
	private String restaurantStreet;
	private String restaurantAddress;
	private String restaurantOpenTime;
	private String restaurantCloseTime;
	private String restaurantIndroduction;
	private String baseURL;
	private String viewURL;
	private String contractURL;
	private String licenseURL;
	private String attorneyURL;
	private String bossPhone;
	private String managerPhone;
	private String bankAccountName;
	private String bankAccountBank;
	private String bankAccountAccount;
	private int id;
	
	
	// setter and getter
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getRestaurantType() {
		return restaurantType;
	}
	public void setRestaurantType(String restaurantType) {
		this.restaurantType = restaurantType;
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
	public String getRestaurantAddress() {
		return restaurantAddress;
	}
	public void setRestaurantAddress(String restaurantAddress) {
		this.restaurantAddress = restaurantAddress;
	}
	public String getRestaurantOpenTime() {
		return restaurantOpenTime;
	}
	public void setRestaurantOpenTime(String restaurantOpenTime) {
		this.restaurantOpenTime = restaurantOpenTime;
	}
	public String getRestaurantCloseTime() {
		return restaurantCloseTime;
	}
	public void setRestaurantCloseTime(String restaurantCloseTime) {
		this.restaurantCloseTime = restaurantCloseTime;
	}
	public String getRestaurantIndroduction() {
		return restaurantIndroduction;
	}
	public void setRestaurantIndroduction(String restaurantIndroduction) {
		this.restaurantIndroduction = restaurantIndroduction;
	}
	public String getBaseURL() {
		return baseURL;
	}
	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}
	public String getViewURL() {
		return viewURL;
	}
	public void setViewURL(String viewURL) {
		this.viewURL = viewURL;
	}
	public String getContractURL() {
		return contractURL;
	}
	public void setContractURL(String contractURL) {
		this.contractURL = contractURL;
	}
	public String getAttorneyURL() {
		return attorneyURL;
	}
	public void setAttorneyURL(String attorneyURL) {
		this.attorneyURL = attorneyURL;
	}
	public String getBossPhone() {
		return bossPhone;
	}
	public void setBossPhone(String bossPhone) {
		this.bossPhone = bossPhone;
	}
	public String getManagerPhone() {
		return managerPhone;
	}
	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}
	public String getBankAccountName() {
		return bankAccountName;
	}
	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}
	public String getBankAccountBank() {
		return bankAccountBank;
	}
	public void setBankAccountBank(String bankAccountBank) {
		this.bankAccountBank = bankAccountBank;
	}
	public String getBankAccountAccount() {
		return bankAccountAccount;
	}
	public void setBankAccountAccount(String bankAccountAccount) {
		this.bankAccountAccount = bankAccountAccount;
	}
	public List<Map<String, Object>> getMerchantInfo() {
		return merchantInfo;
	}
	public void setMerchantInfo(List<Map<String, Object>> merchantInfo) {
		this.merchantInfo = merchantInfo;
	}
	public List<Map<String, Object>> getAllRestaurantType() {
		return allRestaurantType;
	}
	public void setAllRestaurantType(List<Map<String, Object>> allRestaurantType) {
		this.allRestaurantType = allRestaurantType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLicenseURL() {
		return licenseURL;
	}
	public void setLicenseURL(String licenseURL) {
		this.licenseURL = licenseURL;
	}
	
}
