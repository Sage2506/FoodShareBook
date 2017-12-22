package com.example.sage.foodsharebook.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sage.foodsharebook.R;
import com.example.sage.foodsharebook.models.IngredientResponse;

import java.util.ArrayList;

/**
 * Created by Marisa on 22/12/2017.
 */

public class IngredientsListAdapter extends RecyclerView.Adapter<IngredientsListAdapter.ViewHolder>{
    private ArrayList<IngredientResponse> dataset;
    private Context context;

    public IngredientsListAdapter(Context context){
        this.context = context;
        dataset = new ArrayList<>();
    }

    public void addIngredientsList(ArrayList<IngredientResponse> ingredients){
        dataset.addAll(ingredients);
        notifyDataSetChanged();
    }

    public void addIngredientItem(IngredientResponse ingredient){
        dataset.add(ingredient);
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IngredientsListAdapter.ViewHolder holder, int position) {
        IngredientResponse i = dataset.get(position);
        holder.TvIngredientName.setText(i.getName());
        holder.TvIngredientDesc.setText(i.getDescription());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView IvIngredientPic;
        private TextView TvIngredientName;
        private TextView TvIngredientDesc;
        public ViewHolder(View itemView) {
            super(itemView);
            IvIngredientPic = itemView.findViewById(R.id.iv_ingredient_pic);
            TvIngredientName = itemView.findViewById(R.id.tv_ingredient_name);
            TvIngredientDesc = itemView.findViewById(R.id.tv_ingredient_desc);

        }
    }
}
