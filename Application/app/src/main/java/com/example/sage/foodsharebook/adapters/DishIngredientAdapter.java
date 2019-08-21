package com.example.sage.foodsharebook.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.sage.foodsharebook.R;
import com.example.sage.foodsharebook.models.DishIngredient;

import java.util.ArrayList;
import java.util.List;

public class DishIngredientAdapter extends RecyclerView.Adapter<DishIngredientAdapter.ViewHolder>{
    private ArrayList<DishIngredient> dataset;
    private Context context;

    public DishIngredientAdapter(Context context){
        this.context = context;
        dataset = new ArrayList<>();
    }

    public void addIngredientsList(ArrayList<DishIngredient> ingredients){
        dataset.addAll(ingredients);
        notifyDataSetChanged();
    }

    public void addIngredientItem(DishIngredient ingredient){
        dataset.add(ingredient);
        notifyDataSetChanged();
    }

    public List<DishIngredient> getDataset(){
        return dataset;
    }
    @Override
    public DishIngredientAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dish_ingredient_item,parent,false);
        return new DishIngredientAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DishIngredientAdapter.ViewHolder holder, int position) {
        DishIngredient i = dataset.get(position);
        holder.TvIngredientName.setText(i.getIngredientName());
        holder.TvDishIngredientQuantity.setText(i.getQuantity().toString());
        Glide.with(context)
                .load(i.getIngredientImage())
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.IvDishIngredientPic);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView IvDishIngredientPic;
        private TextView TvIngredientName;
        private TextView TvDishIngredientQuantity;
        private TextView TvDishIngredientMeasure;
        public ViewHolder(View itemView) {
            super(itemView);
            IvDishIngredientPic = itemView.findViewById(R.id.iv_ingredient_pic);
            TvIngredientName = itemView.findViewById(R.id.tv_ingredient_name);
            TvDishIngredientQuantity = itemView.findViewById(R.id.tv_ingredient_quantity);
            TvDishIngredientMeasure = itemView.findViewById(R.id.tv_ingredient_measure);


        }
    }
}
