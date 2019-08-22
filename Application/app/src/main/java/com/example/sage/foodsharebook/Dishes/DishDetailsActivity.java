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
import com.example.sage.foodsharebook.adapters.DishIngredientAdapter;
import com.example.sage.foodsharebook.apiFoodShareBookServices.ApiRetrofit;
import com.example.sage.foodsharebook.models.DishIngredient;

import static com.example.sage.foodsharebook.Config.Constants.*;



public class DishDetailsActivity extends AppCompatActivity {
    private DishIngredientAdapter adapter;
    private ApiRetrofit api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new DishIngredientAdapter(this);
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
        String name = data.getStringExtra(DISH_NAME);
        String description = data.getStringExtra(DISH_DESCRIPTION);
        String recipe = data.getStringExtra(DISH_RECIPE);
        String image = data.getStringExtra(DISH_IMAGE);
        int ingredients = data.getIntExtra(DISH_INGREDIENTS_NUMBER,0);
        Glide.with(this)
                .load(image)
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivPic);
        int[] ingredientsIds = new int[ingredients];
        for(int i = 0 ; i<ingredients; i++){
            ingredientsIds[i] = data.getIntExtra(DISH_INGREDIENT+i,0);
        }


        tvName.setText(name);
        tvDesc.setText(description);
        tvRecipe.setText(recipe);
        Log.i("Dishes details","numero de ingredientes: "+ingredients);
        if(ingredients>0){
            for(int i = 0; i < ingredients; i++){
                adapter.addDishIngredientItem(new DishIngredient(
                                            data.getIntExtra(INGREDIENT_ID+i,-1),
                                            data.getStringExtra(INGREDIENT_NAME+i),
                                            data.getStringExtra(INGREDIENT_IMAGE+i),
                                            data.getIntExtra(MEASURE_ID+i, -1),
                                            data.getFloatExtra(QUANTITY+i,-1.0f)
                ));
            }

        }

    }
}
