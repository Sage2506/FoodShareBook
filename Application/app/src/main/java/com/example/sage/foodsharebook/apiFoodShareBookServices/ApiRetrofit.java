package com.example.sage.foodsharebook.apiFoodShareBookServices;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


import com.example.sage.foodsharebook.adapters.DishesListAdapter;
import com.example.sage.foodsharebook.adapters.IngredientsListAdapter;
import com.example.sage.foodsharebook.models.Dish;
import com.example.sage.foodsharebook.models.DishIngredient;
import com.example.sage.foodsharebook.models.Ingredient;
import com.example.sage.foodsharebook.models.Ingredient;
import com.example.sage.foodsharebook.models.LoginResponse;
import com.example.sage.foodsharebook.models.UserLogin;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static com.example.sage.foodsharebook.Config.Constants.*;

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
                .baseUrl(HEROKU_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(FoodShareBookService.class);
        prefs = context.getSharedPreferences(SHARED_PREFERENCES_NAME,0);
    }

    public void getDishes(final DishesListAdapter dishesListAdapter){
        Call<ArrayList<Dish>> dishesArraylistResponse = service.getAllDishes(prefs.getString(USER_TOKEN,null));

        dishesArraylistResponse.enqueue(new Callback<ArrayList<Dish>>() {
            @Override
            public void onResponse(Call<ArrayList<Dish>> call, Response<ArrayList<Dish>> response) {
                if (response.isSuccessful()){
                    ArrayList<Dish> dishes = response.body();
                    Log.i(TAG, "retrieved, dishes"+ dishes.size());
                    dishesListAdapter.addDishesList(dishes);
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
    public void getIngredients(final IngredientsListAdapter adapter){
        Call<ArrayList<Ingredient>> ingredientsArraylistResponse = service.getAllIngredients(prefs.getString(USER_TOKEN,null));

        ingredientsArraylistResponse.enqueue(new Callback<ArrayList<Ingredient>>() {
            @Override
            public void onResponse(Call<ArrayList<Ingredient>> call, Response<ArrayList<Ingredient>> response) {
                if (response.isSuccessful()){
                    ArrayList<Ingredient> ingredients = response.body();
                    adapter.addIngredientsList(ingredients);
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
    public void postIngredient(String name, String description) {
        Call<Ingredient> call = service.newIngredient(new Ingredient(name, description, null));
        call.enqueue(new Callback<Ingredient>() {
            @Override
            public void onResponse(Call<Ingredient> call, Response<Ingredient> response) {
                if(response.isSuccessful()){
                    Log.i(TAG,"Everything fine");
                }
                else{
                    Log.i(TAG, response.toString());
                }
            }

            @Override
            public void onFailure(Call<Ingredient> call, Throwable t) {
                    Log.i(TAG, t.getMessage());
            }
        });
    }
    public void postDish(String name, String description, String recipe, List<DishIngredient> dishIngredients, final DishCallBack dishCallBack ){
        Call<Dish> call = service.newDish(prefs.getString(USER_TOKEN,null),new Dish(name, recipe, description, prefs.getInt(USER_ID, -1), dishIngredients));
        call.enqueue(new Callback<Dish>() {
            @Override
            public void onResponse(Call<Dish> call, Response<Dish> response) {
                if(response.isSuccessful()){
                    Log.i(TAG,"Everything fine");
                    Dish dish = response.body();
                    dishCallBack.response(true, dish);
                }
                else{
                    Log.i(TAG, response.toString());
                    dishCallBack.response( false, null);
                }
            }

            @Override
            public void onFailure(Call<Dish> call, Throwable t) {
                    Log.i(TAG, t.getMessage());
            }
        });
    }
    /*public void postDishIngredient(int dishId, int ingredientId){
        Call<DishIngredient> call = service.newDishIngredient(new DishIngredient(dishId, ingredientId));
        call.enqueue(new Callback<DishIngredient>() {
            @Override
            public void onResponse(Call<DishIngredient> call, Response<DishIngredient> response) {
                if(response.isSuccessful()){
                    Log.i(TAG,"Everything fine");
                }
                else{
                    Log.i(TAG, response.toString());
                }
            }

            @Override
            public void onFailure(Call<DishIngredient> call, Throwable t) {
                Log.i(TAG, t.getMessage());
            }
        });
    }*/
    public void getIngredientByID(int ingredientId, final IngredientsListAdapter adapter){
        Call<Ingredient> call = service.getIngredient(prefs.getString("token",null),ingredientId);
        call.enqueue(new Callback<Ingredient>() {
            @Override
            public void onResponse(Call<Ingredient> call, Response<Ingredient> response) {
                if(response.isSuccessful()){
                    adapter.addIngredientItem(response.body());

                }
                else{
                    Log.i(TAG, response.toString());
                }
            }

            @Override
            public void onFailure(Call<Ingredient> call, Throwable t) {
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
                    serviceCallBack.response(true, response.body().getAuthToken(), response.body().getUser_id());

                }
                else{
                    Log.i(TAG, response.toString());
                    serviceCallBack.response(false,"", -1);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.i(TAG, t.getMessage());
                serviceCallBack.response(false,"", -1);
            }
        });
    }
    public interface ServiceCallBack{
        void response(Boolean bool, String token, int user_id);
    }

    public interface DishCallBack{
        void response (Boolean bool, Dish dish);
    }
}
