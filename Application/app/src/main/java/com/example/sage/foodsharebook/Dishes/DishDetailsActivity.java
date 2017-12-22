package com.example.sage.foodsharebook.Dishes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.sage.foodsharebook.R;
import com.example.sage.foodsharebook.adapters.IngredientsListAdapter;
import com.example.sage.foodsharebook.apiFoodShareBookServices.ApiRetrofit;

import java.util.ArrayList;

public class DishDetailsActivity extends AppCompatActivity {
    private IngredientsListAdapter adapter;
    private ApiRetrofit api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new IngredientsListAdapter(this);
        api = new ApiRetrofit();

        setContentView(R.layout.activity_dish_details);
        TextView tvName = findViewById(R.id.tv_dish_name);
        TextView tvDesc = findViewById(R.id.tv_dish_desc);
        TextView tvRecipe = findViewById(R.id.tv_dish_recipe);
        RecyclerView recyclerView = findViewById(R.id.rv_ingredients);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);

        Intent data = getIntent();
        String name = data.getStringExtra("name");
        String description = data.getStringExtra("description");
        String recipe = data.getStringExtra("recipe");
        int ingredients = data.getIntExtra("ingredientsSize",0);
        int[] ingredientsIds = new int[ingredients];

        tvName.setText(name);
        tvDesc.setText(description);
        tvRecipe.setText(recipe);
        if(ingredients>0){
            getIngredients(ingredientsIds);
        }

    }

    private void getIngredients(int[] ids){
            for (int i = 0; i<ids.length;i++){
                api.getIngredientByID(ids[i],adapter);
            }
    }
}
