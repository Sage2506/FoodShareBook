package com.example.sage.foodsharebook.models

import com.google.gson.annotations.SerializedName

class LoginResponse {

    @SerializedName("auth_token")
    var authToken: String? = null

    @SerializedName("user_id")
    var user_id: Int = 0
}