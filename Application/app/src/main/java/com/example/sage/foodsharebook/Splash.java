package com.example.sage.foodsharebook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sage.foodsharebook.Dishes.FoodListActivity;
import com.example.sage.foodsharebook.Users.LoginActivity;
import static com.example.sage.foodsharebook.Config.Constants.*;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences prefs = getApplicationContext().getSharedPreferences(SHARED_PREFERENCES_NAME,0);
        super.onCreate(savedInstanceState);
        String token = prefs.getString(USER_TOKEN,null);
        Intent intent;
        if (token == null){ intent = new Intent(this,LoginActivity.class); }
        else{ intent = new Intent(this, FoodListActivity.class); }
        startActivity(intent);
        finish();
    }
}
