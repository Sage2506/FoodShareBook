package com.example.sage.foodsharebook.models

import com.google.gson.annotations.SerializedName


class Ingredient(@field:SerializedName("name")
                 var name: String?, @field:SerializedName("description")
                 var description: String?, @field:SerializedName("measures")
                 var measures: List<Int>?) {

    @SerializedName("image")
    var image: String? = null

    @SerializedName("id")
    var id: Int = 0

    val measuresString: String
        get() {
            var result = ""
            for (m in this.measures!!) {
                result += "$m,"
            }
            return result
        }
}