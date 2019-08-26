package com.example.sage.foodsharebook.apiFoodShareBookServices

import android.content.Context
import android.content.SharedPreferences
import android.util.Log


import com.example.sage.foodsharebook.adapters.DishIngredientAdapter
import com.example.sage.foodsharebook.adapters.DishesListAdapter
import com.example.sage.foodsharebook.adapters.IngredientsListAdapter
import com.example.sage.foodsharebook.models.Dish
import com.example.sage.foodsharebook.models.DishIngredient
import com.example.sage.foodsharebook.models.Ingredient
import com.example.sage.foodsharebook.models.LoginResponse
import com.example.sage.foodsharebook.models.UserLogin

import java.util.ArrayList

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.sage.foodsharebook.Config.Constants.*

/**
 * Created by Marisa on 20/12/2017.
 */

class ApiRetrofit(context: Context) {
    var retrofit: Retrofit
    internal val service: FoodShareBookService
    internal var prefs: SharedPreferences


    init {
        retrofit = Retrofit.Builder()
                .baseUrl(HEROKU_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        service = retrofit.create(FoodShareBookService::class.java!!)
        prefs = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0)
    }

    fun getDishes(dishesListAdapter: DishesListAdapter) {
        val dishesArraylistResponse = service.getAllDishes(prefs.getString(USER_TOKEN, null))

        dishesArraylistResponse.enqueue(object : Callback<ArrayList<Dish>> {
            override fun onResponse(call: Call<ArrayList<Dish>>, response: Response<ArrayList<Dish>>) {
                if (response.isSuccessful) {
                    val dishes = response.body()
                    Log.i(TAG, "retrieved, dishes" + dishes!!.size)
                    dishesListAdapter.addDishesList(dishes)
                } else {
                    Log.e(TAG, response.toString())
                }
            }

            override fun onFailure(call: Call<ArrayList<Dish>>, t: Throwable) {
                Log.e(TAG, t.message)
            }
        })
    }

    fun getIngredients(adapter: IngredientsListAdapter) {
        val ingredientsArraylistResponse = service.getAllIngredients(prefs.getString(USER_TOKEN, null))

        ingredientsArraylistResponse.enqueue(object : Callback<ArrayList<Ingredient>> {
            override fun onResponse(call: Call<ArrayList<Ingredient>>, response: Response<ArrayList<Ingredient>>) {
                if (response.isSuccessful) {
                    val ingredients = response.body()
                    adapter.addIngredientsList(ingredients)
                    Log.i(TAG, "Objetivo cumplido")
                } else {
                    Log.e(TAG, response.toString())
                }
            }

            override fun onFailure(call: Call<ArrayList<Ingredient>>, t: Throwable) {
                Log.e(TAG, t.message)
            }
        })
    }

    fun postIngredient(name: String, description: String) {
        val call = service.newIngredient(Ingredient(name, description, null))
        call.enqueue(object : Callback<Ingredient> {
            override fun onResponse(call: Call<Ingredient>, response: Response<Ingredient>) {
                if (response.isSuccessful) {
                    Log.i(TAG, "Everything fine")
                } else {
                    Log.i(TAG, response.toString())
                }
            }

            override fun onFailure(call: Call<Ingredient>, t: Throwable) {
                Log.i(TAG, t.message)
            }
        })
    }

    fun postDish(newDish: Dish, dishCallBack: DishCallBack) {
        val call = service.newDish(prefs.getString(USER_TOKEN, null), newDish)
        call.enqueue(object : Callback<Dish> {
            override fun onResponse(call: Call<Dish>, response: Response<Dish>) {
                if (response.isSuccessful) {
                    Log.i(TAG, "Everything fine")
                    val dish = response.body()
                    dishCallBack.response(true, dish)
                } else {
                    Log.i(TAG, response.toString())
                    dishCallBack.response(false, null)
                }
            }

            override fun onFailure(call: Call<Dish>, t: Throwable) {
                Log.i(TAG, t.message)
            }
        })
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
    /*public void getIngredientByID(int ingredientId, final DishIngredientAdapter adapter){
        Call<Ingredient> call = service.getIngredient(prefs.getString("token",null),ingredientId);
        call.enqueue(new Callback<Ingredient>() {
            @Override
            public void onResponse(Call<Ingredient> call, Response<Ingredient> response) {
                if(response.isSuccessful()){
                    adapter.addDishIngredientItem(response.body());

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
    }*/
    fun logIn(email: String, password: String, serviceCallBack: ServiceCallBack) {
        val call = service.uerLogin(UserLogin(email, password))
        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    Log.i(TAG, response.body()!!.authToken)
                    serviceCallBack.response(true, response.body()!!.authToken, response.body()!!.user_id)

                } else {
                    Log.i(TAG, response.toString())
                    serviceCallBack.response(false, "", -1)
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.i(TAG, t.message)
                serviceCallBack.response(false, "", -1)
            }
        })
    }

    interface ServiceCallBack {
        fun response(bool: Boolean?, token: String?, user_id: Int)
    }

    interface DishCallBack {
        fun response(bool: Boolean?, dish: Dish?)
    }

    companion object {
        internal val TAG = "ApiRetrofit"
    }
}
