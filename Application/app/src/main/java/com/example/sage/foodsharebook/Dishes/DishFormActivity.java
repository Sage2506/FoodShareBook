package com.example.sage.foodsharebook.Dishes;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sage.foodsharebook.R;
import com.example.sage.foodsharebook.adapters.DishIngredientAdapter;
import com.example.sage.foodsharebook.adapters.DishesListAdapter;
import com.example.sage.foodsharebook.apiFoodShareBookServices.ApiRetrofit;
import com.example.sage.foodsharebook.models.Dish;
import com.example.sage.foodsharebook.models.DishIngredient;
import com.example.sage.foodsharebook.models.Ingredient;

import java.util.ArrayList;
import java.util.List;

import static com.example.sage.foodsharebook.Config.Constants.*;

public class DishFormActivity extends AppCompatActivity {
    private String TAG = "DishFormActivity";

    private EditText name;
    private EditText recipe;
    private EditText description;
    private ApiRetrofit api;
    private Button btnCreate;
    private Button btnAddIngredient;
    private RecyclerView recyclerView;
    private DishIngredientAdapter adapter;
    SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_form);

        api = new ApiRetrofit(this);


        name = findViewById(R.id.te_dish_name);
        recipe = findViewById(R.id.te_dish_recipe);
        description = findViewById(R.id.te_dish_desc);
        btnAddIngredient = findViewById(R.id.btn_add_ingredient);
        btnCreate = findViewById(R.id.btn_create_dish);
        recyclerView = findViewById(R.id.rv_ingredients);

        adapter = new DishIngredientAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager( this, 1);
        recyclerView.setLayoutManager(layoutManager);


        ArrayList<DishIngredient> fakeIngredients = new ArrayList<>();
        fakeIngredients.add(new DishIngredient(10, "ingrediente 8", "https://cdn0.iconfinder.com/data/icons/food-filled-outline-2/64/ingredient-food-shopping-512.png", 1, 2));
        fakeIngredients.add(new DishIngredient(11, "ingrediente 8", "https://cdn0.iconfinder.com/data/icons/food-filled-outline-2/64/ingredient-food-shopping-512.png", 2, 2));
        fakeIngredients.add(new DishIngredient(12, "ingrediente 8", "https://cdn0.iconfinder.com/data/icons/food-filled-outline-2/64/ingredient-food-shopping-512.png", 1, 2));
        fakeIngredients.add(new DishIngredient(9, "ingrediente 8", "https://cdn0.iconfinder.com/data/icons/food-filled-outline-2/64/ingredient-food-shopping-512.png", 1, 2));
        fakeIngredients.add(new DishIngredient(13, "ingrediente 8", "https://cdn0.iconfinder.com/data/icons/food-filled-outline-2/64/ingredient-food-shopping-512.png", 1, 2));
        adapter.addIngredientsList(fakeIngredients);



        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableInputs(false);
                createIngredient();

            }
        });
    }

    private void enableInputs(boolean status){
        name.setEnabled(status);
        recipe.setEnabled(status);
        description.setEnabled(status);
        btnCreate.setEnabled(status);
    }

    private void clearFields(){
        name.setText("");
        recipe.setText("");
        description.setText("");
    }

    private void createIngredient(){
        prefs = getApplicationContext().getSharedPreferences(SHARED_PREFERENCES_NAME,0);
        //TODO: create dish form
        api.postDish(name.getText().toString().trim(), description.getText().toString().trim(), recipe.getText().toString().trim(), adapter.getDataset(), new ApiRetrofit.DishCallBack() {
            @Override
            public void response(Boolean bool, Dish dish) {
                if(bool){
                    Log.i(TAG,"Dish created id: "+dish.getId());
                    clearFields();


                } else {
                    Log.i(TAG,"Something went wrong");
                }
            }
        });
        enableInputs(true);
    }

}
