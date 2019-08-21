package com.example.sage.foodsharebook.models;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;


public class Dish implements Serializable {

	@SerializedName("dish_ingredients")
	private List<DishIngredient> dishIngredients;

	@SerializedName("image")
	private String image;

	@SerializedName("user_id")
	private Integer userId;

	@SerializedName("name")
	private String name;

	@SerializedName("recipe")
	private String recipe;

	@SerializedName("description")
	private String description;

	@SerializedName("id")
	private Integer id;

	public Dish(String name, String recipe, String description, int userId, List<DishIngredient> dishIngredients){
		this.name = name;
		this.recipe = recipe;
		this.description = description;
		this.userId = userId;
		this.dishIngredients = dishIngredients;
	}

	public void setDishIngredients(List<DishIngredient> dishIngredients){
		this.dishIngredients = dishIngredients;
	}


	public List<DishIngredient> getDishIngredients(){
		return dishIngredients;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public Object getUserId(){
		return userId;
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