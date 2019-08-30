package com.example.sage.foodsharebook.Dishes

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.sage.foodsharebook.Config.Constants.DISH_DESCRIPTION
import com.example.sage.foodsharebook.Config.Constants.DISH_IMAGE
import com.example.sage.foodsharebook.Config.Constants.DISH_INGREDIENT
import com.example.sage.foodsharebook.Config.Constants.DISH_INGREDIENTS_NUMBER
import com.example.sage.foodsharebook.Config.Constants.DISH_NAME
import com.example.sage.foodsharebook.Config.Constants.DISH_RECIPE
import com.example.sage.foodsharebook.Config.Constants.INGREDIENT_ID
import com.example.sage.foodsharebook.Config.Constants.INGREDIENT_IMAGE
import com.example.sage.foodsharebook.Config.Constants.INGREDIENT_NAME
import com.example.sage.foodsharebook.Config.Constants.MEASURE_ID
import com.example.sage.foodsharebook.Config.Constants.QUANTITY

import com.example.sage.foodsharebook.R
import com.example.sage.foodsharebook.adapters.DishIngredientAdapter
import com.example.sage.foodsharebook.apiFoodShareBookServices.ApiRetrofit
import com.example.sage.foodsharebook.models.DishIngredient




class DishDetailsActivity : AppCompatActivity() {
    private var adapter: DishIngredientAdapter? = null
    private var api: ApiRetrofit? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = DishIngredientAdapter(this)
        api = ApiRetrofit(this)

        setContentView(R.layout.activity_dish_details)
        val ivPic = findViewById<ImageView>(R.id.iv_dish_pic)
        val tvName = findViewById<TextView>(R.id.tv_dish_name)
        val tvDesc = findViewById<TextView>(R.id.tv_dish_desc)
        val tvRecipe = findViewById<TextView>(R.id.tv_dish_recipe)
        val recyclerView = findViewById<RecyclerView>(R.id.rv_ingredients)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(this, 1)
        recyclerView.layoutManager = layoutManager


        val data = intent
        val name = data.getStringExtra(DISH_NAME)
        val description = data.getStringExtra(DISH_DESCRIPTION)
        val recipe = data.getStringExtra(DISH_RECIPE)
        val image = data.getStringExtra(DISH_IMAGE)
        val ingredients = data.getIntExtra(DISH_INGREDIENTS_NUMBER, 0)
        Glide.with(this)
                .load(image)
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivPic)
        val ingredientsIds = IntArray(ingredients)
        for (i in 0 until ingredients) {
            ingredientsIds[i] = data.getIntExtra(DISH_INGREDIENT + i, 0)
        }


        tvName.text = name
        tvDesc.text = description
        tvRecipe.text = recipe
        Log.i("Dishes details", "numero de ingredientes: $ingredients")
        if (ingredients > 0) {
            for (i in 0 until ingredients) {
                adapter!!.addDishIngredientItem(DishIngredient(
                        data.getIntExtra(INGREDIENT_ID + i, -1),
                        data.getStringExtra(INGREDIENT_NAME + i),
                        data.getStringExtra(INGREDIENT_IMAGE + i),
                        data.getIntExtra(MEASURE_ID + i, -1),
                        data.getFloatExtra(QUANTITY + i, -1.0f)
                ))
            }

        }

    }
}
