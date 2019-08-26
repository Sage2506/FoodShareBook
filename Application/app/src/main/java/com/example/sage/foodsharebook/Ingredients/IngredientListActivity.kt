package com.example.sage.foodsharebook.Ingredients

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log

import com.example.sage.foodsharebook.R
import com.example.sage.foodsharebook.adapters.IngredientsListAdapter
import com.example.sage.foodsharebook.apiFoodShareBookServices.ApiRetrofit
import com.example.sage.foodsharebook.models.Ingredient
import com.example.sage.foodsharebook.Config.Constants.*

class IngredientListActivity : AppCompatActivity() {
    private val TAG = "IngredientListActivity"
    internal var rv: RecyclerView
    internal var adapter: IngredientsListAdapter
    internal var api: ApiRetrofit
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredient_list)
        rv = findViewById(R.id.rv_ingredients)
        api = ApiRetrofit(this)
        adapter = IngredientsListAdapter(this)
        rv.adapter = adapter
        rv.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(this, 1)
        rv.layoutManager = layoutManager

        adapter.setListener { ingredient ->
            val intent = Intent(applicationContext, IngredientMeasureActivity::class.java)
            intent.putExtra(INGREDIENT_ID, ingredient.id)
            intent.putExtra(INGREDIENT_IMAGE, ingredient.image)
            intent.putExtra(INGREDIENT_MEASURES, ingredient.measuresString)
            intent.putExtra(INGREDIENT_NAME, ingredient.name)
            startActivityForResult(intent, CREATE_INGREDIENT_MEASURE_REQUEST)
        }

        api.getIngredients(adapter)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CREATE_INGREDIENT_MEASURE_REQUEST && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                setResult(Activity.RESULT_OK, data)
                finish()
            }
        }
    }

    companion object {

        internal val CREATE_INGREDIENT_MEASURE_REQUEST = 1
    }
}
