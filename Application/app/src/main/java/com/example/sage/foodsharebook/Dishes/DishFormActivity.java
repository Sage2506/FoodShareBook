package com.example.sage.foodsharebook.Dishes;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sage.foodsharebook.R;
import com.example.sage.foodsharebook.apiFoodShareBookServices.ApiRetrofit;
import com.example.sage.foodsharebook.models.DishResponse;
import static com.example.sage.foodsharebook.Config.Constants.*;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class DishFormActivity extends AppCompatActivity {
    private String TAG = "DishFormActivity";

    private EditText name;
    private EditText recipe;
    private EditText description;
    private ApiRetrofit api;
    private Button btnCreate;
    SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_form);

        api = new ApiRetrofit(this);


        name = findViewById(R.id.te_dish_desc);
        recipe = findViewById(R.id.te_dish_recipe);
        description = findViewById(R.id.te_dish_desc);
        btnCreate = findViewById(R.id.btn_create_dish);

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
        api.postDish(name.getText().toString().trim(), description.getText().toString().trim(), recipe.getText().toString().trim(), new ApiRetrofit.DishCallBack() {
            @Override
            public void response(Boolean bool, DishResponse dish) {
                if(bool){
                    Log.i(TAG,"Dish created succesfully");
                    clearFields();


                } else {
                    Log.i(TAG,"Something went wrong");
                }
            }
        });
        enableInputs(true);
    }

}
