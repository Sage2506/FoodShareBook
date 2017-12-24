package com.example.sage.foodsharebook.apiFoodShareBookServices;

import com.example.sage.foodsharebook.models.Dish;
import com.example.sage.foodsharebook.models.DishIngredient;
import com.example.sage.foodsharebook.models.DishIngredientResponse;
import com.example.sage.foodsharebook.models.DishResponse;
import com.example.sage.foodsharebook.models.Ingredient;
import com.example.sage.foodsharebook.models.IngredientResponse;
import com.example.sage.foodsharebook.models.LoginResponse;
import com.example.sage.foodsharebook.models.UserLogin;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Marisa on 20/12/2017.
 */

public interface FoodShareBookService {
    @GET("dishes")
    Call<ArrayList<DishResponse>> getAllDishes(@Header("Authorization") String token);

    @POST("dishes")
    Call<DishResponse> newDish(@Body Dish body);

    @GET("ingredients")
    Call<ArrayList<IngredientResponse>> getAllIngredients();

    @POST("ingredients")
    Call<IngredientResponse> newIngredient(@Body Ingredient body);

    @POST("dish_ingredients")
    Call<DishIngredientResponse> newDishIngredient(@Body DishIngredient body);

    @GET("ingredients/{INGREDIENT_ID}")
    Call<IngredientResponse> getIngredient(@Path("INGREDIENT_ID") int ingredient_id);

    @POST("users/login")
    Call<LoginResponse> uerLogin(@Body UserLogin body);

}
