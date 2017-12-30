package com.example.sage.foodsharebook.Dishes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
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
        api = new ApiRetrofit(this);

        setContentView(R.layout.activity_dish_details);
        ImageView ivPic = findViewById(R.id.iv_dish_pic);
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
        String image = data.getStringExtra("image");
        int ingredients = data.getIntExtra("ingredientsSize",0);
        Glide.with(this)
                .load(image)
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivPic);
        int[] ingredientsIds = new int[ingredients];
        for(int i = 0 ; i<ingredients; i++){
            ingredientsIds[i] = data.getIntExtra("ingredient"+i,0);
        }


        tvName.setText(name);
        tvDesc.setText(description);
        tvRecipe.setText(recipe);
        Log.i("Dishes details","numero de ingredientes: "+ingredients);
        if(ingredients>0){
            getIngredients(ingredientsIds);
        }

    }

    private void getIngredients(int[] ids){
            for (int i = 0; i<ids.length;i++){
                Log.i("Dish details","id de: "+ids[i]);
                api.getIngredientByID(ids[i],adapter);
            }
    }
}
