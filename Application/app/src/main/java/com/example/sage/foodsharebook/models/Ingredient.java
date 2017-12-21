package com.example.sage.foodsharebook.models;

import com.google.gson.annotations.SerializedName;

public class Ingredient{

	@SerializedName("image")
	private Object image;

	@SerializedName("name")
	private String name;

	@SerializedName("description")
	private String description;


	public Ingredient(String name, String description){
		this.name = name;
		this.description = description;
	}

	public void setImage(Object image){
		this.image = image;
	}

	public Object getImage(){
		return image;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	@Override
	public String toString(){
		return "Ingredient{ "+
				"name='" + name + '\'' +
				", description='" + description+ '\'' +
				'}';

	}
}