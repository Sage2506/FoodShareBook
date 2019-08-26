package com.example.sage.foodsharebook.Ingredients

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast

import com.example.sage.foodsharebook.Config.Constants.*

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.sage.foodsharebook.R
import com.example.sage.foodsharebook.models.DishIngredient

import java.util.ArrayList

class IngredientMeasureActivity : AppCompatActivity() {
    private var tv_ingredient_name: TextView? = null
    private var et_quantity: EditText? = null
    private var iv_ingredient_image: ImageView? = null
    private var rg_measures: RadioGroup? = null
    private var btn_add_ingredient: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredient_measure)

        iv_ingredient_image = findViewById(R.id.iv_ingredient_pic)
        tv_ingredient_name = findViewById(R.id.tv_ingredient_name)
        et_quantity = findViewById(R.id.et_measure_quantity)
        rg_measures = findViewById(R.id.rg_measures)
        btn_add_ingredient = findViewById(R.id.btn_add_ingredient)


        val intent = intent
        val ingredient_name = intent.getStringExtra(INGREDIENT_NAME)
        val ingredient_image = intent.getStringExtra(INGREDIENT_IMAGE)
        val measuresString = intent.getStringExtra(INGREDIENT_MEASURES)
        if (!measuresString.isEmpty()) {
            val measures = ArrayList<Int>()
            for (measure in measuresString.split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()) {
                measures.add(Integer.parseInt(measure))
            }
            makeRadioButtons(measures, rg_measures)
        }


        Glide.with(applicationContext)
                .load(ingredient_image)
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(iv_ingredient_image!!)
        tv_ingredient_name!!.text = ingredient_name

        btn_add_ingredient!!.setOnClickListener {
            if (et_quantity!!.text.toString().isEmpty() || rg_measures!!.checkedRadioButtonId == -1) {
                Toast.makeText(applicationContext, "Fields missing", Toast.LENGTH_SHORT).show()
            } else {
                val measure_id = findViewById<View>(rg_measures!!.checkedRadioButtonId).tag as Int
                Log.i(TAG, "Mostrando ingrediente creado")
                val dishIngredient = DishIngredient(
                        intent.getIntExtra(INGREDIENT_ID, -1),
                        ingredient_name,
                        ingredient_image,
                        measure_id,
                        java.lang.Float.parseFloat(et_quantity!!.text.toString().trim { it <= ' ' })
                )
                val intent = Intent()
                intent.putExtra(INGREDIENT_NAME, dishIngredient.ingredientName)
                intent.putExtra(INGREDIENT_IMAGE, dishIngredient.ingredientImage)
                intent.putExtra(QUANTITY, dishIngredient.quantity)
                intent.putExtra(MEASURE_ID, dishIngredient.measureId)
                intent.putExtra(INGREDIENT_ID, dishIngredient.ingredientId)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }

    }

    private fun makeRadioButtons(measures: List<Int>, radioGroup: RadioGroup?) {
        var i = 0
        for (measure in measures) {
            val rb = RadioButton(this)
            rb.text = measureString(measure)
            rb.tag = measure
            radioGroup!!.addView(rb)
            i++
        }
    }

    private fun measureString(measure: Int): String {
        var result = ""
        when (measure) {
            1 -> result = "Piezas"
            2 -> result = "Gramos"
            3 -> result = "Kilogramos"
            4 -> result = "Mililitros"
            5 -> result = "Litros"
            6 -> result = "Cucharadas"
            7 -> result = "Tazas"
            8 -> result = "Rebanadas"
            9 -> result = "Pieza"
            else -> result = ""
        }
        return result
    }

    companion object {
        private val TAG = "IngredientMeasureActivity"
    }
}
