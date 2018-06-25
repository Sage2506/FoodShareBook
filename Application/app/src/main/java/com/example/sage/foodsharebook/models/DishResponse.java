package com.example.sage.foodsharebook.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DishResponse{

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

	public void setUsersIds(int usersIds){
		this.usersIds = usersIds;
	}

	public int getUsersIds(){
		return usersIds;
	}

	@Override
 	public String toString(){
		return 
			"DishResponse{" + 
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