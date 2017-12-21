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
import com.example.sage.foodsharebook.models.DishResponse;

import java.util.ArrayList;

/**
 * Created by Marisa on 21/12/2017.
 */

public class DishesListAdapter extends RecyclerView.Adapter<DishesListAdapter.ViewHolder> {
    private ArrayList<DishResponse> dataset;
    private Context context;

    public DishesListAdapter(Context context){
        this.context = context;
        dataset = new ArrayList<>();
    }

    public void addDishesList(ArrayList<DishResponse> dishes){
        dataset.addAll(dishes);
        notifyDataSetChanged();
    }

    @Override
    public DishesListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dish_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DishesListAdapter.ViewHolder holder, int position) {
        DishResponse d = dataset.get(position);
        holder.TvDishName.setText(d.getName());
        holder.TvDishDesc.setText(d.getDescription());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView IvDishPic;
        private TextView TvDishName;
        private TextView TvDishDesc;
        private CardView cardView;

        public ViewHolder(View itemView){
            super(itemView);

            IvDishPic = itemView.findViewById(R.id.iv_dish_pic);
            TvDishName = itemView.findViewById(R.id.tv_dish_name);
            TvDishDesc = itemView.findViewById(R.id.tv_dish_desc);
            cardView = (CardView) itemView;
        }
}

}
