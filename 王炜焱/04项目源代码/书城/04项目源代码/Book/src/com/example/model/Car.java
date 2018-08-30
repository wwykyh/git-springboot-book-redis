package com.example.model;

public class Car {
	private int car_Id;
	private int car_Userid;
	private int car_Bookid;
	private String car_Images;
	private String car_Bookname;
	private float car_Price;
	private int car_Number;
	private float car_Total;
	public int getCar_Id() {
		return car_Id;
	}
	public void setCar_Id(int car_Id) {
		this.car_Id = car_Id;
	}
	public int getCar_Userid() {
		return car_Userid;
	}
	public void setCar_Userid(int car_Userid) {
		this.car_Userid = car_Userid;
	}
	public int getCar_Bookid() {
		return car_Bookid;
	}
	public void setCar_Bookid(int car_Bookid) {
		this.car_Bookid = car_Bookid;
	}
	public String getCar_Images() {
		return car_Images;
	}
	public void setCar_Images(String car_Images) {
		this.car_Images = car_Images;
	}
	public String getCar_Bookname() {
		return car_Bookname;
	}
	public void setCar_Bookname(String car_Bookname) {
		this.car_Bookname = car_Bookname;
	}
	public float getCar_Price() {
		return car_Price;
	}
	public void setCar_Price(float car_Price) {
		this.car_Price = car_Price;
	}
	public int getCar_Number() {
		return car_Number;
	}
	public void setCar_Number(int car_Number) {
		this.car_Number = car_Number;
	}
	public float getCar_Total() {
		return car_Total;
	}
	public void setCar_Total(float car_Total) {
		this.car_Total = car_Total;
	}
	@Override
	public String toString() {
		return "Car [car_Id=" + car_Id + ", car_Userid=" + car_Userid
				+ ", car_Bookid=" + car_Bookid + ", car_Images=" + car_Images
				+ ", car_Bookname=" + car_Bookname + ", car_Price=" + car_Price
				+ ", car_Number=" + car_Number + ", car_Total=" + car_Total
				+ "]";
	}

	
	
} 
