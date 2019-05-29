package com.example.recipebook;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.recipebook.model.Category;
import com.example.recipebook.adapter.CategoryListAdapter;
import com.example.recipebook.model.CategoryResponse;
import com.example.recipebook.network.ApiClient;
import com.example.recipebook.network.ApiInterface;
import com.example.recipebook.viewmodel.CategoryViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  {

    @BindView(R.id.rv_category)
     RecyclerView rvCategory;

    @BindView(R.id.progress_loader)
     ProgressBar progressBar;



    private ArrayList<Category> list;


    public static final String EXTRA_MESSAGE =
            "com.example.android.RecipeBook.extra.MESSAGE";

    @BindView(R.id.container)
    ConstraintLayout constraintLayout;

    private CategoryListAdapter categoryListAdapter;



    private CategoryViewModel categoryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        progressBar.setVisibility(View.INVISIBLE);

        list = new ArrayList<>();

        rvCategory.setLayoutManager(new GridLayoutManager(this, 2));
        categoryListAdapter = new CategoryListAdapter(this);
        categoryListAdapter.setListCategory(list);
        rvCategory.setAdapter(categoryListAdapter);

        categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);

//        getData();

        categoryViewModel.getAllCategory().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(@Nullable final List<Category> categories) {
                // Update the cached copy of the words in the adapter.
                categoryListAdapter.setCategory((ArrayList<Category>) categories);
                list = (ArrayList<Category>) categories;
            }
        });

        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelected(list.get(position));
            }
        });
    }

    private void showSelected(Category category) {
        Intent intent = new Intent(this, MealActivity.class);
        intent.putExtra(EXTRA_MESSAGE, category.getStrCategory());
        startActivity(intent);
    }

}

