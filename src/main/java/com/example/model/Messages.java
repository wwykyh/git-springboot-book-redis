package com.example.model;

public class Messages {
	private int messageId;
	private String messageName;
	private String messageEmail;
	private String messagePhone;
	private String messageAddress;
	private String message;
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public String getMessageName() {
		return messageName;
	}
	public void setMessageName(String messageName) {
		this.messageName = messageName;
	}
	public String getMessageEmail() {
		return messageEmail;
	}
	public void setMessageEmail(String messageEmail) {
		this.messageEmail = messageEmail;
	}
	public String getMessagePhone() {
		return messagePhone;
	}
	public void setMessagePhone(String messagePhone) {
		this.messagePhone = messagePhone;
	}
	public String getMessageAddress() {
		return messageAddress;
	}
	public void setMessageAddress(String messageAddress) {
		this.messageAddress = messageAddress;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", messageName="
				+ messageName + ", messageEmail=" + messageEmail
				+ ", messagePhone=" + messagePhone + ", messageAddress="
				+ messageAddress + ", message=" + message + "]";
	}
}
