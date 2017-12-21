package com.example.sage.foodsharebook.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Dish{

	@SerializedName("ingredient_ids")
	private List<Integer> ingredientIds;

	@SerializedName("name")
	private String name;

	@SerializedName("recipe")
	private String recipe;

	@SerializedName("description")
	private String description;

	@SerializedName("id")
	private int id;

	public void setIngredientIds(List<Integer> ingredientIds){
		this.ingredientIds = ingredientIds;
	}

	public List<Integer> getIngredientIds(){
		return ingredientIds;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setRecipe(String recipe){
		this.recipe = recipe;
	}

	public String getRecipe(){
		return recipe;
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