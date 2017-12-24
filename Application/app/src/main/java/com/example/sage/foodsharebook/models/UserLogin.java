package com.example.sage.foodsharebook.models;

import com.google.gson.annotations.SerializedName;

public class UserLogin{

	@SerializedName("password")
	private String password;

	@SerializedName("email")
	private String email;

	public UserLogin(String email, String password){
		this.email=email;
		this.password=password;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public void setEmail(String email){
		this.email = email;
	}
}