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
import com.example.sage.foodsharebook.models.Dish

import java.util.ArrayList

/**
 * Created by Marisa on 21/12/2017.
 */

class DishesListAdapter(private val context: Context) : RecyclerView.Adapter<DishesListAdapter.ViewHolder>() {
    private val dataset: ArrayList<Dish>
    private var listener: Listener? = null

    interface Listener {
        fun openDish(dish: Dish)
    }

    fun setListener(listener: Listener) {
        this.listener = listener
    }

    init {
        dataset = ArrayList()
    }

    fun addDishesList(dishes: ArrayList<Dish>) {
        dataset.addAll(dishes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dish_item, parent, false)

        view.setOnClickListener { view ->
            if (listener != null) {
                val d = dataset[view.tag as Int]
                listener!!.openDish(d)
            }
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val d = dataset[position]
        holder.TvDishName.text = d.name
        holder.TvDishDesc.text = d.description
        holder.cardView.tag = position
        Glide.with(context)
                .load(d.image)
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.IvDishPic)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val IvDishPic: ImageView
        private val TvDishName: TextView
        private val TvDishDesc: TextView
        private val cardView: CardView

        init {

            IvDishPic = itemView.findViewById(R.id.iv_dish_pic)
            TvDishName = itemView.findViewById(R.id.tv_dish_name)
            TvDishDesc = itemView.findViewById(R.id.tv_dish_desc)
            cardView = itemView as CardView
        }
    }

}
