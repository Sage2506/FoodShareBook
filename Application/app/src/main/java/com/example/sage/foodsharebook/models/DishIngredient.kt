package com.example.sage.foodsharebook.models


import com.google.gson.annotations.SerializedName

import java.io.Serializable

class DishIngredient(@field:SerializedName("ingredient_id")
                     var ingredientId: Int, @field:SerializedName("ingredient_name")
                     var ingredientName: String?, @field:SerializedName("ingredient_image")
                     var ingredientImage: String?, @field:SerializedName("measure_id")
                     var measureId: Int?, @field:SerializedName("quantity")
                     private var quantity: Float) : Serializable {

    fun setQuantity(quantity: Float?) {
        this.quantity = quantity!!
    }

    fun getQuantity(): Float? {
        return quantity
    }

    override fun toString(): String {
        return "DishIngredientsItem{" +
                "quantity = '" + quantity + '\''.toString() +
                ",ingredient_id = '" + ingredientId + '\''.toString() +
                ",ingredient_name = '" + ingredientName + '\''.toString() +
                ",ingredient_image = '" + ingredientImage + '\''.toString() +
                ",measure_id = '" + measureId + '\''.toString() +
                "}"
    }
}