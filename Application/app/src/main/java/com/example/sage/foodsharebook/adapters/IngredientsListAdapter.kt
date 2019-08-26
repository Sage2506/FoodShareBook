package com.example.sage.foodsharebook.adapters

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.sage.foodsharebook.R
import com.example.sage.foodsharebook.models.Ingredient

import java.util.ArrayList

/**
 * Created by Marisa on 22/12/2017.
 */

class IngredientsListAdapter(private val context: Context) : RecyclerView.Adapter<IngredientsListAdapter.ViewHolder>() {
    private val dataset: ArrayList<Ingredient>
    private var listener: Listener? = null

    interface Listener {
        fun openIngredient(ingredient: Ingredient)
    }

    fun setListener(listener: Listener) {
        this.listener = listener
    }

    init {
        dataset = ArrayList()
    }

    fun addIngredientsList(ingredients: ArrayList<Ingredient>) {
        dataset.addAll(ingredients)
        notifyDataSetChanged()
    }

    fun addIngredientItem(ingredient: Ingredient) {
        dataset.add(ingredient)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ingredient_item, parent, false)


        view.setOnClickListener { view ->
            if (listener != null) {
                val i = dataset[view.tag as Int]
                listener!!.openIngredient(i)
            }
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: IngredientsListAdapter.ViewHolder, position: Int) {
        val i = dataset[position]
        holder.TvIngredientName.text = i.name
        holder.TvIngredientDesc.text = i.description
        holder.cardView.tag = position
        Glide.with(context)
                .load(i.image)
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.IvIngredientPic)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val IvIngredientPic: ImageView
        private val TvIngredientName: TextView
        private val TvIngredientDesc: TextView
        private val cardView: CardView

        init {
            IvIngredientPic = itemView.findViewById(R.id.iv_ingredient_pic)
            TvIngredientName = itemView.findViewById(R.id.tv_ingredient_name)
            TvIngredientDesc = itemView.findViewById(R.id.tv_ingredient_desc)
            cardView = itemView as CardView

        }
    }
}
