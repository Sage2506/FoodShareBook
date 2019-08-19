package com.example.sage.foodsharebook.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Dish{

	@SerializedName("image")
	private String image;

	@SerializedName("name")
	private String name;

	@SerializedName("recipe")
	private String recipe;

	@SerializedName("description")
	private String description;

	@SerializedName("ingredients")
	private List<IngredientsItem> ingredients;

	@SerializedName("id")
	private int id;

	@SerializedName("users_ids")
	private int usersIds;

	@SerializedName("user_id")
	private int user_id;

	public Dish(String name, String recipe, String description, int user_id){
		this.name = name;
		this.recipe = recipe;
		this.description = description;
		this.user_id = user_id;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
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

	public void setIngredients(List<IngredientsItem> ingredients){
		this.ingredients = ingredients;
	}

	public List<IngredientsItem> getIngredients(){
		return ingredients;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public void setUsersIds(int usersIds){
		this.usersIds = usersIds;
	}

	public int getUsersIds(){
		return usersIds;
	}

	@Override
 	public String toString(){
		return 
			"Dish{" + 
			"image = '" + image + '\'' + 
			",name = '" + name + '\'' + 
			",recipe = '" + recipe + '\'' + 
			",description = '" + description + '\'' + 
			",ingredients = '" + ingredients + '\'' + 
			",id = '" + id + '\'' + 
			",users_ids = '" + usersIds + '\'' + 
			"}";
		}
}