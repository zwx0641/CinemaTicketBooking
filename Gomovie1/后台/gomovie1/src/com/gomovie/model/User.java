package com.gomovie.model;

public class User {
	int id;
	String username;
	String password;
	String email;
	int result;
	int age;
	
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getAge() {
	   return age;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	
	public User() {
		super();
	}
	public User(String username, String password, int age, String email) {
		super();
		this.username = username;
		this.password = password;
		this.age = age;
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}
	
	
}
