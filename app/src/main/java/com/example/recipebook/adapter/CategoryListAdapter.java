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
import com.example.recipebook.model.Category;
import com.example.recipebook.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.GridViewHolder> {
    private Context context;
    private ArrayList<Category> listCategories;

    public CategoryListAdapter(Context context) {
        this.context = context;
    }

    private ArrayList<Category> getListCategory() {
        return listCategories;
    }

    public void setListCategory(ArrayList<Category> listQueue) {
        this.listCategories = listQueue;
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewHolder holder, int position) {
        holder.tvCategory.setText(getListCategory().get(position).getStrCategory());

        Glide.with(context)
                .load(getListCategory().get(position).getStrCategoryThumb())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.loading)
                        .dontAnimate()
                        .centerCrop())
                .into(holder.ivCategory);
    }

    public void setCategory(ArrayList<Category> category){
        listCategories = category;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return getListCategory().size();
    }

    class GridViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCategory;
        TextView tvCategory;

        GridViewHolder(View itemView) {
            super(itemView);
            ivCategory = itemView.findViewById(R.id.iv_category);
            tvCategory = itemView.findViewById(R.id.tv_category);
        }
    }

}

