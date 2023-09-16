package com.dnb.eCommerce.exceptions;

public class NotUniqueNameException extends Exception {
	public NotUniqueNameException(String msg) {
		super(msg);
	}
	
	@Override
	public String toString() {
		return super.toString()+super.getMessage();
	}
}
