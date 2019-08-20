package com.example.sage.foodsharebook.models;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DishIngredient implements Serializable {

	@SerializedName("quantity")
	private Object quantity;

	@SerializedName("ingredient_id")
	private int ingredientId;

	@SerializedName("ingredient_name")
	private String ingredientName;

	@SerializedName("ingredient_image")
	private String ingredientImage;

	@SerializedName("measure_id")
	private Object measureId;

	public void setQuantity(Object quantity){
		this.quantity = quantity;
	}

	public Object getQuantity(){
		return quantity;
	}

	public void setIngredientId(int ingredientId){
		this.ingredientId = ingredientId;
	}

	public int getIngredientId(){
		return ingredientId;
	}

	public void setIngredientName(String ingredientName){
		this.ingredientName = ingredientName;
	}

	public String getIngredientName(){
		return ingredientName;
	}

	public void setIngredientImage(String ingredientImage){
		this.ingredientImage = ingredientImage;
	}

	public String getIngredientImage(){
		return ingredientImage;
	}

	public void setMeasureId(Object measureId){
		this.measureId = measureId;
	}

	public Object getMeasureId(){
		return measureId;
	}
}