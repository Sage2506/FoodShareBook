package com.example.sage.foodsharebook.apiFoodShareBookServices;

import android.util.Log;

import com.example.sage.foodsharebook.models.Dish;
import com.example.sage.foodsharebook.models.Ingredient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Marisa on 20/12/2017.
 */

public class ApiRetrofit {
    final static String TAG = "ApiRetrofit";
    public Retrofit retrofit;
    final FoodShareBookService service;

    public ApiRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://thawing-citadel-50826.herokuapp.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(FoodShareBookService.class);
    }

    public void getDishes(){
        Call<ArrayList<Dish>> dishesArraylistResponse = service.getAllDishes();

        dishesArraylistResponse.enqueue(new Callback<ArrayList<Dish>>() {
            @Override
            public void onResponse(Call<ArrayList<Dish>> call, Response<ArrayList<Dish>> response) {
                if (response.isSuccessful()){
                    ArrayList<Dish> dishes = response.body();
                    for (Dish d : dishes)
                    {
                        Log.i(TAG,d.getName()+d.getIngredientIds());
                    }
                    Log.i(TAG,"Objetivo cumplido");
                }
                else{
                    Log.e(TAG,response.toString());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Dish>> call, Throwable t) {
                    Log.e(TAG,t.getMessage());
            }
        });
    }
    public void getIngredients(){
        Call<ArrayList<Ingredient>> ingredientsArraylistResponse = service.getAllIngredients();

        ingredientsArraylistResponse.enqueue(new Callback<ArrayList<Ingredient>>() {
            @Override
            public void onResponse(Call<ArrayList<Ingredient>> call, Response<ArrayList<Ingredient>> response) {
                if (response.isSuccessful()){
                    ArrayList<Ingredient> ingredients = response.body();
                    Log.i(TAG, "Objetivo cumplido");
                }
                else {
                    Log.e(TAG, response.toString());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Ingredient>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }
}
