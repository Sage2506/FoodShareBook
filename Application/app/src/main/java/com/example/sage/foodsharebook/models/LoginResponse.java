package com.example.sage.foodsharebook.models;

import com.google.gson.annotations.SerializedName;

public class LoginResponse{

	@SerializedName("auth_token")
	private String authToken;

	public void setAuthToken(String authToken){
		this.authToken = authToken;
	}

	public String getAuthToken(){
		return authToken;
	}
}