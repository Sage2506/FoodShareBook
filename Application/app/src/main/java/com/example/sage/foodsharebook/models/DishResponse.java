package com.example.sage.foodsharebook.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DishResponse{

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

	@SerializedName("image")
	private String image;

	public List<Integer> getIngredientIds(){
		return ingredientIds;
	}

	public String getName(){
		return name;
	}

	public String getRecipe(){
		return recipe;
	}

	public String getDescription(){
		return description;
	}

	public int getId(){
		return id;
	}

	public String getImage(){return image;}
}