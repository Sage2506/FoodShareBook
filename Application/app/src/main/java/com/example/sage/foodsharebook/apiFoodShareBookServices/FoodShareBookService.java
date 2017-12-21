package com.example.sage.foodsharebook.apiFoodShareBookServices;

import com.example.sage.foodsharebook.models.Dish;
import com.example.sage.foodsharebook.models.Ingredient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Marisa on 20/12/2017.
 */

public interface FoodShareBookService {
    @GET("dishes")
    Call<ArrayList<Dish>> getAllDishes();

    @GET("ingredients")
    Call<ArrayList<Ingredient>> getAllIngredients();
}
