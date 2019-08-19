package com.example.sage.foodsharebook.models;

import com.google.gson.annotations.SerializedName;

public class LoginResponse{

	@SerializedName("auth_token")
	private String authToken;

	@SerializedName("user_id")
	private int user_id;

	public void setAuthToken(String authToken){
		this.authToken = authToken;
	}

	public String getAuthToken(){
		return authToken;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
}