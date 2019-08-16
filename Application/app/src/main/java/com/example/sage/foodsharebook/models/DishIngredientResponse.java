package com.example.sage.foodsharebook.models;

import com.google.gson.annotations.SerializedName;

public class DishIngredientResponse{

	@SerializedName("dish_id")
	private int dishId;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("ingredient_id")
	private int ingredientId;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	public int getDishId(){
		return dishId;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public int getIngredientId(){
		return ingredientId;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public int getId(){
		return id;
	}
}