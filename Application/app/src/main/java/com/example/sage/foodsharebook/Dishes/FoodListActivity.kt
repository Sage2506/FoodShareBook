package com.example.sage.foodsharebook.Dishes

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.Menu
import android.view.MenuItem

import com.example.sage.foodsharebook.R
import com.example.sage.foodsharebook.adapters.DishesListAdapter
import com.example.sage.foodsharebook.apiFoodShareBookServices.ApiRetrofit
import com.example.sage.foodsharebook.models.Dish
import com.example.sage.foodsharebook.models.DishIngredient

import com.example.sage.foodsharebook.Config.Constants.*


// TODO: implement a refresh swipe
class FoodListActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var dishesListAdapter: DishesListAdapter? = null
    private var api: ApiRetrofit? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_list)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        api = ApiRetrofit(this)


        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener {
            val form = Intent(applicationContext, DishFormActivity::class.java)
            startActivity(form)
        }

        recyclerView = findViewById(R.id.rv_dishes)
        dishesListAdapter = DishesListAdapter(this)
        recyclerView!!.adapter = dishesListAdapter
        recyclerView!!.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(this, 1)
        recyclerView!!.layoutManager = layoutManager

        dishesListAdapter!!.setListener { dish ->
            val dishScreen = Intent(applicationContext, DishDetailsActivity::class.java)
            dishScreen.putExtra(DISH_NAME, dish.name)
            dishScreen.putExtra("description", dish.description)
            dishScreen.putExtra("recipe", dish.recipe)
            if (dish.dishIngredients != null)
                dishScreen.putExtra("ingredientsSize", dish.dishIngredients!!.size)
            dishScreen.putExtra("image", dish.image)
            var i = 0
            if (dish.dishIngredients != null)
                for (dishIngredient in dish.dishIngredients!!) {
                    dishScreen.putExtra(INGREDIENT_NAME + i, dishIngredient.ingredientName)
                    dishScreen.putExtra(INGREDIENT_IMAGE + i, dishIngredient.ingredientImage)
                    dishScreen.putExtra(MEASURE_ID + i, dishIngredient.measureId)
                    dishScreen.putExtra(QUANTITY + i, dishIngredient.quantity)
                    i++
                }
            startActivity(dishScreen)
        }

        api!!.getDishes(dishesListAdapter)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_food_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }
}
