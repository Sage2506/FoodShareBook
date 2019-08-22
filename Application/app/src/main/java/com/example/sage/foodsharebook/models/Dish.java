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

	public Dish(String name, String recipe, String description, String image,  List<DishIngredient> dishIngredients){
		this.name = name;
		this.recipe = recipe;
		this.description = description;
		this.dishIngredients = dishIngredients;
		this.image = image;
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

	@Override
	public String toString(){
		return
				"Response{" +
						",id = '" + id + '\'' +
						"dish_ingredients = '" + dishIngredients + '\'' +
						",image = '" + image + '\'' +
						",user_id = '" + userId + '\'' +
						",name = '" + name + '\'' +
						",recipe = '" + recipe + '\'' +
						",description = '" + description + '\'' +
						"}";
	}
}