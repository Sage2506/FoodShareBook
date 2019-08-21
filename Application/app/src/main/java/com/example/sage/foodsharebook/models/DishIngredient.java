package com.example.sage.foodsharebook.models;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DishIngredient implements Serializable {

	@SerializedName("quantity")
	private float quantity;

	@SerializedName("ingredient_id")
	private int ingredientId;

	@SerializedName("ingredient_name")
	private String ingredientName;

	@SerializedName("ingredient_image")
	private String ingredientImage;

	@SerializedName("measure_id")
	private Integer measureId;

	public DishIngredient(int ingredientId, String ingredientName, String ingredientImage, int measureId, float quantity ){
		this.ingredientId = ingredientId;
		this.ingredientName = ingredientName;
		this.ingredientImage = ingredientImage;
		this.measureId = measureId;
		this.quantity = quantity;
	}

	public void setQuantity(Float quantity){
		this.quantity = quantity;
	}

	public Float getQuantity(){
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

	public void setMeasureId(Integer measureId){
		this.measureId = measureId;
	}

	public Integer getMeasureId(){
		return measureId;
	}
}