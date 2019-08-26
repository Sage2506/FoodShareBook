package com.example.sage.foodsharebook.models

import java.io.Serializable
import com.google.gson.annotations.SerializedName


class Dish(@field:SerializedName("name")
           var name: String?, @field:SerializedName("recipe")
           var recipe: String?, @field:SerializedName("description")
           var description: String?, @field:SerializedName("image")
           var image: String?, @field:SerializedName("dish_ingredients")
           var dishIngredients: List<DishIngredient>?) : Serializable {

    @SerializedName("user_id")
    private var userId: Int? = null

    @SerializedName("id")
    private var id: Int? = null

    fun setUserId(userId: Int) {
        this.userId = userId
    }

    fun getUserId(): Any? {
        return userId
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getId(): Int {
        return id!!
    }

    override fun toString(): String {
        return "Response{" +
                ",id = '" + id + '\''.toString() +
                "dish_ingredients = '" + dishIngredients + '\''.toString() +
                ",image = '" + image + '\''.toString() +
                ",user_id = '" + userId + '\''.toString() +
                ",name = '" + name + '\''.toString() +
                ",recipe = '" + recipe + '\''.toString() +
                ",description = '" + description + '\''.toString() +
                "}"
    }
}