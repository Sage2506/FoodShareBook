package com.example.sage.foodsharebook.apiFoodShareBookServices;

import com.example.sage.foodsharebook.models.Dish;
import com.example.sage.foodsharebook.models.Ingredient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Marisa on 20/12/2017.
 */

public interface FoodShareBookService {
    @GET("dishes")
    Call<ArrayList<Dish>> getAllDishes();

    @GET("ingredients")
    Call<ArrayList<Ingredient>> getAllIngredients();

    @POST("ingredients")
    @FormUrlEncoded
    Call<Ingredient> newIngredient(@Body Ingredient body);
}
