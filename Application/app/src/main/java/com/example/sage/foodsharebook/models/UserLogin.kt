package com.example.sage.foodsharebook.models

import com.google.gson.annotations.SerializedName

class UserLogin(@field:SerializedName("email")
                private var email: String?, @field:SerializedName("password")
                private var password: String?) {

    fun setPassword(password: String) {
        this.password = password
    }

    fun setEmail(email: String) {
        this.email = email
    }
}