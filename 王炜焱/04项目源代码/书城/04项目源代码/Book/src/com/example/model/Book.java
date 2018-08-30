package com.example.model;

import java.sql.Date;

public class Book {
	private int bookId;
	private int booktypeId;
	private String bookName;
	private String bookAuthor;
	private String bookEdition;
	private float bookPrice;
	private int bookAmount;
	private String bookIntroduce;
	private String issuanceDate;
	private String bookImages;
	private int bookSpecil;
	private float bookDiscount;
	private Category category;
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public int getBookSpecil() {
		return bookSpecil;
	}
	public void setBookSpecil(int bookSpecil) {
		this.bookSpecil = bookSpecil;
	}
	public float getBookDiscount() {
		return bookDiscount;
	}
	public void setBookDiscount(float bookDiscount) {
		this.bookDiscount = bookDiscount;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getBooktypeId() {
		return booktypeId;
	}
	public void setBooktypeId(int booktypeId) {
		this.booktypeId = booktypeId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public String getBookEdition() {
		return bookEdition;
	}
	public void setBookEdition(String bookEdition) {
		this.bookEdition = bookEdition;
	}
	public float getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(float bookPrice) {
		this.bookPrice = bookPrice;
	}
	public int getBookAmount() {
		return bookAmount;
	}
	public void setBookAmount(int bookAmount) {
		this.bookAmount = bookAmount;
	}
	public String getBookIntroduce() {
		return bookIntroduce;
	}
	public void setBookIntroduce(String bookIntroduce) {
		this.bookIntroduce = bookIntroduce;
	}
	public String getIssuanceDate() {
		return issuanceDate;
	}
	public void setIssuanceDate(String issuanceDate) {
		this.issuanceDate = issuanceDate;
	}
	public String getBookImages() {
		return bookImages;
	}
	public void setBookImages(String bookImages) {
		this.bookImages = bookImages;
	}
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", booktypeId=" + booktypeId
				+ ", bookName=" + bookName + ", bookAuthor=" + bookAuthor
				+ ", bookEdition=" + bookEdition + ", bookPrice=" + bookPrice
				+ ", bookAmount=" + bookAmount + ", bookIntroduce="
				+ bookIntroduce + ", issuanceDate=" + issuanceDate
				+ ", bookImages=" + bookImages + ", bookSpecil=" + bookSpecil
				+ ", bookDiscount=" + bookDiscount + ", category=" + category
				+ "]";
	}
	
	
}
