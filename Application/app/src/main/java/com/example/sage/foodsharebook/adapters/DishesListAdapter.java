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
import com.example.sage.foodsharebook.models.DishResponse;

import java.util.ArrayList;

/**
 * Created by Marisa on 21/12/2017.
 */

public class DishesListAdapter extends RecyclerView.Adapter<DishesListAdapter.ViewHolder> {
    private ArrayList<DishResponse> dataset;
    private Context context;
    private Listener listener;

    public interface Listener{
        void openDish(DishResponse dish);
    }

    public void setListener(Listener listener){ this.listener = listener;}

    public DishesListAdapter(Context context){
        this.context = context;
        dataset = new ArrayList<>();
    }

    public void addDishesList(ArrayList<DishResponse> dishes){
        dataset.addAll(dishes);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dish_item,parent,false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null){
                    DishResponse d = dataset.get((int)view.getTag());
                    listener.openDish(d);
                }
            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DishResponse d = dataset.get(position);
        holder.TvDishName.setText(d.getName());
        holder.TvDishDesc.setText(d.getDescription());
        holder.cardView.setTag(position);
        Glide.with(context)
                .load(d.getImage())
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.IvDishPic);
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
