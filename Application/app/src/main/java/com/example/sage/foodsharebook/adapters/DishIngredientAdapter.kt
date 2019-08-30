package com.example.sage.foodsharebook.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.sage.foodsharebook.R
import com.example.sage.foodsharebook.models.DishIngredient

import java.util.ArrayList

class DishIngredientAdapter(private val context: Context) : RecyclerView.Adapter<DishIngredientAdapter.ViewHolder>() {
    private val dataset: ArrayList<DishIngredient>

    init {
        dataset = ArrayList()
    }

    fun clearDataset() {
        this.dataset.clear()
        notifyDataSetChanged()
    }

    fun addIngredientsList(ingredients: ArrayList<DishIngredient>) {
        dataset.addAll(ingredients)
        notifyDataSetChanged()
    }

    fun addDishIngredientItem(ingredient: DishIngredient) {
        dataset.add(ingredient)
        notifyDataSetChanged()
    }

    fun getDataset(): List<DishIngredient> {
        return dataset
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishIngredientAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dish_ingredient_item, parent, false)
        return DishIngredientAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: DishIngredientAdapter.ViewHolder, position: Int) {
        val i = dataset[position]
        holder.TvIngredientName.text = i.ingredientName
        holder.TvDishIngredientQuantity.text = i.quantity!!.toString()
        Glide.with(context)
                .load(i.ingredientImage)
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.IvDishIngredientPic)
        holder.TvDishIngredientMeasure.text = measureString(i.measureId!!)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val IvDishIngredientPic: ImageView
        private val TvIngredientName: TextView
        private val TvDishIngredientQuantity: TextView
        private val TvDishIngredientMeasure: TextView

        init {
            IvDishIngredientPic = itemView.findViewById(R.id.iv_ingredient_pic)
            TvIngredientName = itemView.findViewById(R.id.tv_ingredient_name)
            TvDishIngredientQuantity = itemView.findViewById(R.id.tv_ingredient_quantity)
            TvDishIngredientMeasure = itemView.findViewById(R.id.tv_ingredient_measure)


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
}
