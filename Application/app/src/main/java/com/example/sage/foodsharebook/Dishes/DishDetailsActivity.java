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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_details);
        TextView tvName = findViewById(R.id.tv_dish_name);
        TextView tvDesc = findViewById(R.id.tv_dish_desc);
        TextView tvRecipe = findViewById(R.id.tv_dish_recipe);
        RecyclerView recyclerView = findViewById(R.id.rv_ingredients);
        IngredientsListAdapter adapter = new IngredientsListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);

        ApiRetrofit api = new ApiRetrofit();

        Intent data = getIntent();
        String name = data.getStringExtra("name");
        String description = data.getStringExtra("description");
        String recipe = data.getStringExtra("recipe");
        int ingredients = data.getIntExtra("ingredientsSize",0);

        tvName.setText(name);
        tvDesc.setText(description);
        tvRecipe.setText(recipe);
        if(ingredients>0){
            for (int i = 0; i<ingredients;i++){
                api.getIngredientByID((data.getIntExtra("ingredient"+i,0)),adapter);
            }
        }

    }
}
