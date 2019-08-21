package com.example.sage.foodsharebook.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.sage.foodsharebook.R;
import com.example.sage.foodsharebook.models.Ingredient;

import java.util.ArrayList;

/**
 * Created by Marisa on 22/12/2017.
 */

public class IngredientsListAdapter extends RecyclerView.Adapter<IngredientsListAdapter.ViewHolder>{
    private ArrayList<Ingredient> dataset;
    private Context context;
    private Listener listener;

    public interface Listener{
        void openIngredient(Ingredient ingredient);
    }

    public void setListener(Listener listener){ this.listener = listener;}

    public IngredientsListAdapter(Context context){
        this.context = context;
        dataset = new ArrayList<>();
    }

    public void addIngredientsList(ArrayList<Ingredient> ingredients){
        dataset.addAll(ingredients);
        notifyDataSetChanged();
    }

    public void addIngredientItem(Ingredient ingredient){
        dataset.add(ingredient);
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_item,parent,false);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null){
                    Ingredient i = dataset.get((int)view.getTag());
                    listener.openIngredient(i);
                }
            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IngredientsListAdapter.ViewHolder holder, int position) {
        Ingredient i = dataset.get(position);
        holder.TvIngredientName.setText(i.getName());
        holder.TvIngredientDesc.setText(i.getDescription());
        holder.cardView.setTag(position);
        Glide.with(context)
                .load(i.getImage())
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.IvIngredientPic);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView IvIngredientPic;
        private TextView TvIngredientName;
        private TextView TvIngredientDesc;
        private CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            IvIngredientPic = itemView.findViewById(R.id.iv_ingredient_pic);
            TvIngredientName = itemView.findViewById(R.id.tv_ingredient_name);
            TvIngredientDesc = itemView.findViewById(R.id.tv_ingredient_desc);
            cardView = (CardView) itemView;

        }
    }
}
