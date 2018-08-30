package com.example.model;



public class Orders {
	private int orderId;
	private int bookId;
	private int userId;
	private int bookCount;
	private float bookPrice;
	private String orderTime;
	private Book book;
	private Users users;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getBookCount() {
		return bookCount;
	}
	public void setBookCount(int bookCount) {
		this.bookCount = bookCount;
	}
	public float getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(float bookPrice) {
		this.bookPrice = bookPrice;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	
	
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", bookId=" + bookId
				+ ", userId=" + userId + ", bookCount=" + bookCount
				+ ", bookPrice=" + bookPrice + ", orderTime=" + orderTime
				+ ", book=" + book + ", users=" + users + "]";
	}

}
