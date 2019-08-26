package com.example.sage.foodsharebook.apiFoodShareBookServices

import com.example.sage.foodsharebook.models.Dish
import com.example.sage.foodsharebook.models.Ingredient
import com.example.sage.foodsharebook.models.LoginResponse
import com.example.sage.foodsharebook.models.UserLogin

import java.util.ArrayList

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Created by Marisa on 20/12/2017.
 */

interface FoodShareBookService {
    @GET("dishes")
    fun getAllDishes(@Header("Authorization") token: String): Call<ArrayList<Dish>>

    @POST("dishes")
    fun newDish(@Header("Authorization") token: String, @Body body: Dish): Call<Dish>

    @GET("ingredients")
    fun getAllIngredients(@Header("Authorization") token: String): Call<ArrayList<Ingredient>>

    @POST("ingredients")
    fun newIngredient(@Body body: Ingredient): Call<Ingredient>

    /*@POST("dish_ingredients")
    Call<DishIngredient> newDishIngredient(@Body DishIngredient body);*/

    @GET("ingredients/{INGREDIENT_ID}")
    fun getIngredient(@Header("Authorization") token: String, @Path("INGREDIENT_ID") ingredient_id: Int): Call<Ingredient>

    @POST("users/login")
    fun uerLogin(@Body body: UserLogin): Call<LoginResponse>

}
