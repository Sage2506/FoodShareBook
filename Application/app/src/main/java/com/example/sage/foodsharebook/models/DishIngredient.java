package com.example.sage.foodsharebook.models;

import com.google.gson.annotations.SerializedName;

public class DishIngredient{

	@SerializedName("dish_id")
	private int dishId;

	@SerializedName("ingredient_id")
	private int ingredientId;

	public DishIngredient(int dishId, int ingredientId){
		this.dishId = dishId;
		this.ingredientId = ingredientId;
	}

	public void setDishId(int dishId){
		this.dishId = dishId;
	}

	public int getDishId(){
		return dishId;
	}

	public void setIngredientId(int ingredientId){
		this.ingredientId = ingredientId;
	}

	public int getIngredientId(){
		return ingredientId;
	}
}