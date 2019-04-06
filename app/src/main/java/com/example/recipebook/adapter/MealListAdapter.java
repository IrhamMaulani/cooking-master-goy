package com.example.recipebook.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.recipebook.Model.Meal;
import com.example.recipebook.R;

import java.util.ArrayList;

public class MealListAdapter extends RecyclerView.Adapter<MealListAdapter.GridViewHolder> {
    private Context context;
    private ArrayList<Meal> listMeal;

    public MealListAdapter(Context context) {
        this.context = context;
    }

    private ArrayList<Meal> getListMeal() {
        return listMeal;
    }

    public void setListMeal(ArrayList<Meal> listMeal) {
        this.listMeal = listMeal;
    }

    @NonNull
    @Override
    public MealListAdapter.GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item, parent, false);
        return new MealListAdapter.GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealListAdapter.GridViewHolder holder, int position) {
        holder.tvRecipe.setText(getListMeal().get(position).getStrMeal());


        Glide.with(context)
                .load( getListMeal().get(position).getStrMealThumb())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.loading)
                        .dontAnimate()
                        .override(500, 400)
                        .fitCenter())
                .into(holder.ivRecipe);
    }

    @Override
    public int getItemCount() {
        return getListMeal().size();
    }

    class GridViewHolder extends RecyclerView.ViewHolder {
        ImageView ivRecipe;
        TextView tvRecipe;

        GridViewHolder(View itemView) {
            super(itemView);
            ivRecipe = itemView.findViewById(R.id.iv_recipe);
            tvRecipe = itemView.findViewById(R.id.tv_recipe);
        }
    }
}