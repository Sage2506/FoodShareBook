package com.example.sage.foodsharebook.Dishes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sage.foodsharebook.R;
import com.example.sage.foodsharebook.adapters.DishesListAdapter;
import com.example.sage.foodsharebook.apiFoodShareBookServices.ApiRetrofit;
import com.example.sage.foodsharebook.models.DishResponse;

public class FoodListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DishesListAdapter dishesListAdapter;
    private ApiRetrofit api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        api = new ApiRetrofit(this);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        recyclerView = findViewById(R.id.rv_dishes);
        dishesListAdapter = new DishesListAdapter(this);
        recyclerView.setAdapter(dishesListAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);

        dishesListAdapter.setListener(new DishesListAdapter.Listener() {
            @Override
            public void openDish(DishResponse dish) {
                Intent dishScreen = new Intent(getApplicationContext(),DishDetailsActivity.class);
                dishScreen.putExtra("name",dish.getName());
                dishScreen.putExtra("description", dish.getDescription());
                dishScreen.putExtra("recipe", dish.getRecipe());
                dishScreen.putExtra("ingredientsSize",dish.getIngredientIds().size());
                dishScreen.putExtra("image",dish.getImage());
                for(int i= 0; i< dish.getIngredientIds().size(); i++){
                    dishScreen.putExtra("ingredient"+i,dish.getIngredientIds().get(i));
                }
                startActivity(dishScreen);

            }
        });

        api.getDishes(dishesListAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_food_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
