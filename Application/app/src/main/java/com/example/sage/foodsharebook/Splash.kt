package com.example.sage.foodsharebook

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.example.sage.foodsharebook.Dishes.FoodListActivity
import com.example.sage.foodsharebook.Users.LoginActivity

import com.example.sage.foodsharebook.Config.Constants.SHARED_PREFERENCES_NAME
import com.example.sage.foodsharebook.Config.Constants.USER_TOKEN

class Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val prefs = applicationContext.getSharedPreferences(SHARED_PREFERENCES_NAME, 0)
        super.onCreate(savedInstanceState)
        val token = prefs.getString(USER_TOKEN, null)
        val intent: Intent
        if (token == null) {
            intent = Intent(this, LoginActivity::class.java)
        } else {
            intent = Intent(this, FoodListActivity::class.java)
        }
        startActivity(intent)
        finish()
    }
}
