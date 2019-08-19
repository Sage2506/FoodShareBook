package com.example.sage.foodsharebook.Ingredients;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.sage.foodsharebook.R;
import com.example.sage.foodsharebook.adapters.IngredientsListAdapter;
import com.example.sage.foodsharebook.apiFoodShareBookServices.ApiRetrofit;

public class IngredientListActivity extends AppCompatActivity {
    RecyclerView rv;
    IngredientsListAdapter adapter;
    ApiRetrofit api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_list);
        rv = findViewById(R.id.rv_ingredients);
        api = new ApiRetrofit(this);
        adapter = new IngredientsListAdapter(this);
        rv.setAdapter(adapter);
        rv.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        rv.setLayoutManager(layoutManager);

        api.getIngredients(adapter);
    }
}
