package com.pos.web.entity;

import java.util.Date;

public class Product {
	private int number;									// 번호
	private String code;								// 상품코드
	private String name;								// 상품명
	private int volume;									// 수량
	private int unitPrice;								// 단가
	private int totalPrice;								// 금액
	private Date date;									// 입고일
	private int sumTotalPrice;							// 총액
	
	public Product() {
	}
	
	public Product(String code, String name, int volume, int unitPrice) {
		this.code = code;
		this.name = name;
		this.volume = volume;
		this.unitPrice = unitPrice;
	}
	
	public Product(String code, String name, int volume, int unitPrice, int totalPrice) {
		this.code = code;
		this.name = name;
		this.volume = volume;
		this.unitPrice = unitPrice;
		this.totalPrice = totalPrice;
	}
	
	public Product(String code, String name, int volume, int unitPrice, Date date) {
		this.code = code;
		this.name = name;
		this.volume = volume;
		this.unitPrice = unitPrice;
		this.date = date;
	}
	
	public Product(int sumTotalPrice) {
		this.sumTotalPrice = sumTotalPrice;
	}
	
	public Product(int number, String code, String name, int volume, int unitPrice, int totalPrice, Date date) {
		this.number = number;
		this.code = code;
		this.name = name;
		this.volume = volume;
		this.unitPrice = unitPrice;
		this.totalPrice = totalPrice;
		this.date = date;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getSumTotalPrice() {
		return sumTotalPrice;
	}

	public void setSumTotalPrice(int sumTotalPrice) {
		this.sumTotalPrice = sumTotalPrice;
	}
	
	

}
