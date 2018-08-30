package com.example.model;

import java.sql.Date;

public class Users {
	private int userId;
	private String userName;
	private String password;
	private String userEmail;
	private String userPhone;
	private String userAdress;
	private Date userDate;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserAdress() {
		return userAdress;
	}
	public void setUserAdress(String userAdress) {
		this.userAdress = userAdress;
	}
	public Date getUserDate() {
		return userDate;
	}
	public void setUserDate(Date userDate) {
		this.userDate = userDate;
	}
	@Override
	public String toString() {
		return "Users [userId=" + userId + ", userName=" + userName + ",  password=" + password + ", userEmail=" + userEmail
				+ ", userPhone=" + userPhone + ", userAdress=" + userAdress
				+ ", userDate=" + userDate + "]";
	}
}
