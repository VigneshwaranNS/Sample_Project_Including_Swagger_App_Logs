package com.example.demo.exception;

public class UserDefinedExeption extends Exception {

	
	private String message;
	
	public UserDefinedExeption(String msg) {
	
		this.message=msg;
	}
	

	@Override
	public String toString() {
		return "UserDefinedExeption [message=" + message + "]";
	}
	
	
	
}
