package com.unfame.Model;

public class Account {
	private String username;
	private String email;
	private String password;
	
	public Account() {
		
	}
	
	public Account(String username, String password, String email) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public Account(String password, String email) {
		this.email = email;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
