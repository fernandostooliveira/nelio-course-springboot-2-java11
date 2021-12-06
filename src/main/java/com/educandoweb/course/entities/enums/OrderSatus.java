package com.educandoweb.course.entities.enums;

public enum OrderSatus {

	
	WAITING_PAYMENT (1),
	PAID (2),
	SHIPPED(3),
	DELIVERED(4),
	CANCELED(5),;
	
	private int code;

	private OrderSatus(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static OrderSatus valueOf (int code) {
		for (OrderSatus value : OrderSatus.values()) {
			if (value.getCode()== code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid OrderSatus code");
	}
	
}
