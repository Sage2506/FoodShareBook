package com.example.sage.foodsharebook.apiFoodShareBookServices;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.sage.foodsharebook.adapters.DishesListAdapter;
import com.example.sage.foodsharebook.adapters.IngredientsListAdapter;
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
    SharedPreferences prefs;


    public ApiRetrofit(Context context){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://foodsharebook.herokuapp.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(FoodShareBookService.class);
        prefs = context.getSharedPreferences("MyPrefs",0);
    }

    public void getDishes(final DishesListAdapter dishesListAdapter){
        Call<ArrayList<DishResponse>> dishesArraylistResponse = service.getAllDishes(prefs.getString("token",null));

        dishesArraylistResponse.enqueue(new Callback<ArrayList<DishResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<DishResponse>> call, Response<ArrayList<DishResponse>> response) {
                if (response.isSuccessful()){
                    ArrayList<DishResponse> dishes = response.body();
                    dishesListAdapter.addDishesList(dishes);
                }
                else{
                    Log.e(TAG,response.toString());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<DishResponse>> call, Throwable t) {
                    Log.e(TAG,t.getMessage());
            }
        });
    }
    public void getIngredients(){
        Call<ArrayList<IngredientResponse>> ingredientsArraylistResponse = service.getAllIngredients(prefs.getString("token",null));

        ingredientsArraylistResponse.enqueue(new Callback<ArrayList<IngredientResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<IngredientResponse>> call, Response<ArrayList<IngredientResponse>> response) {
                if (response.isSuccessful()){
                    ArrayList<IngredientResponse> ingredients = response.body();
                    Log.i(TAG, "Objetivo cumplido");
                }
                else {
                    Log.e(TAG, response.toString());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<IngredientResponse>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }
    public void postIngredient(String name, String description) {
        Call<IngredientResponse> call = service.newIngredient(new Ingredient(name, description));
        call.enqueue(new Callback<IngredientResponse>() {
            @Override
            public void onResponse(Call<IngredientResponse> call, Response<IngredientResponse> response) {
                if(response.isSuccessful()){
                    Log.i(TAG,"Everything fine");
                }
                else{
                    Log.i(TAG, response.toString());
                }
            }

            @Override
            public void onFailure(Call<IngredientResponse> call, Throwable t) {
                    Log.i(TAG, t.getMessage());
            }
        });
    }
    public void postDish(String name, String description, String recipe){
        Call<DishResponse> call = service.newDish(new Dish(name, recipe, description));
        call.enqueue(new Callback<DishResponse>() {
            @Override
            public void onResponse(Call<DishResponse> call, Response<DishResponse> response) {
                if(response.isSuccessful()){
                    Log.i(TAG,"Everything fine");
                }
                else{
                    Log.i(TAG, response.toString());
                }
            }

            @Override
            public void onFailure(Call<DishResponse> call, Throwable t) {
                    Log.i(TAG, t.getMessage());
            }
        });
    }
    public void postDishIngredient(int dishId, int ingredientId){
        Call<DishIngredientResponse> call = service.newDishIngredient(new DishIngredient(dishId, ingredientId));
        call.enqueue(new Callback<DishIngredientResponse>() {
            @Override
            public void onResponse(Call<DishIngredientResponse> call, Response<DishIngredientResponse> response) {
                if(response.isSuccessful()){
                    Log.i(TAG,"Everything fine");
                }
                else{
                    Log.i(TAG, response.toString());
                }
            }

            @Override
            public void onFailure(Call<DishIngredientResponse> call, Throwable t) {
                Log.i(TAG, t.getMessage());
            }
        });
    }
    public void getIngredientByID(int ingredientId, final IngredientsListAdapter adapter){
        Call<IngredientResponse> call = service.getIngredient(prefs.getString("token",null),ingredientId);
        call.enqueue(new Callback<IngredientResponse>() {
            @Override
            public void onResponse(Call<IngredientResponse> call, Response<IngredientResponse> response) {
                if(response.isSuccessful()){
                    adapter.addIngredientItem(response.body());

                }
                else{
                    Log.i(TAG, response.toString());
                }
            }

            @Override
            public void onFailure(Call<IngredientResponse> call, Throwable t) {
                Log.i(TAG, t.getMessage());
            }
        });
    }
    public void logIn(String email, String password, final ServiceCallBack serviceCallBack){
        Call<LoginResponse> call = service.uerLogin(new UserLogin(email, password));
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    Log.i(TAG, response.body().getAuthToken());
                    serviceCallBack.response(true, response.body().getAuthToken());

                }
                else{
                    Log.i(TAG, response.toString());
                    serviceCallBack.response(false,"");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.i(TAG, t.getMessage());
                serviceCallBack.response(false,"");
            }
        });
    }
    public interface ServiceCallBack{
        void response(Boolean bool, String token);
    }
}
