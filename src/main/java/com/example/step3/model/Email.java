package com.example.step3.model;

public class Email {
	
	private String email;
	private int hashcode;
	
	public Email(String email, int hashcode){
		this.email = email;
		this.hashcode = hashcode;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getHashcode() {
		return hashcode;
	}
	public void setHashcode(int hashcode) {
		this.hashcode = hashcode;
	}
	
}
