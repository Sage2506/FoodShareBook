package com.example.sage.foodsharebook.Ingredients;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.sage.foodsharebook.R;
import com.example.sage.foodsharebook.adapters.IngredientsListAdapter;
import com.example.sage.foodsharebook.apiFoodShareBookServices.ApiRetrofit;
import com.example.sage.foodsharebook.models.Ingredient;
import static com.example.sage.foodsharebook.Config.Constants.*;

public class IngredientListActivity extends AppCompatActivity {
    private final String TAG = "IngredientListActivity";
    RecyclerView rv;
    IngredientsListAdapter adapter;
    ApiRetrofit api;

    static final int CREATE_INGREDIENT_MEASURE_REQUEST = 1;
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

        adapter.setListener(new IngredientsListAdapter.Listener() {
            @Override
            public void openIngredient(Ingredient ingredient) {
                Intent intent = new Intent(getApplicationContext(),IngredientMeasureActivity.class);
                intent.putExtra(INGREDIENT_ID,ingredient.getId());
                intent.putExtra(INGREDIENT_IMAGE,ingredient.getImage());
                intent.putExtra(INGREDIENT_MEASURES,ingredient.getMeasuresString());
                intent.putExtra(INGREDIENT_NAME,ingredient.getName());
                startActivityForResult(intent,CREATE_INGREDIENT_MEASURE_REQUEST );
            }
        });

        api.getIngredients(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CREATE_INGREDIENT_MEASURE_REQUEST && resultCode == Activity.RESULT_OK)
        {
            if(data!=null){
                setResult(Activity.RESULT_OK, data);
                finish();
            }
        }
    }
}
