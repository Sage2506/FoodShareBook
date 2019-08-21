package com.example.sage.foodsharebook.models;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class Ingredient{

	@SerializedName("image")
	private String image;

	@SerializedName("measures")
	private List<Integer> measures;

	@SerializedName("name")
	private String name;

	@SerializedName("description")
	private String description;

	@SerializedName("id")
	private int id;

	public Ingredient(String name, String description, List<Integer> measures){
		this.name = name;
		this.description = description;
		this.measures = measures;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setMeasures(List<Integer> measures){
		this.measures = measures;
	}

	public List<Integer> getMeasures(){
		return measures;
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

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}
}