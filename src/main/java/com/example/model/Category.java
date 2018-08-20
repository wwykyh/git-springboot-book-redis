package com.example.model;

public class Category {
	private int typeId;
	private String typeName;
	private String typeIntroduce;
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeIntroduce() {
		return typeIntroduce;
	}
	public void setTypeIntroduce(String typeIntroduce) {
		this.typeIntroduce = typeIntroduce;
	}
	@Override
	public String toString() {
		return "Category [typeId=" + typeId + ", typeName=" + typeName
				+ ", typeIntroduce=" + typeIntroduce + "]";
	}
}
