package com.example.recipebook;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.recipebook.Model.Categories;
import com.example.recipebook.adapter.CategoryListAdapter;
import com.example.recipebook.async.FetchCategoryBook;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView rvCategory;

    private ProgressBar progressBar;

    private ArrayList<Categories> list;

    public static final String EXTRA_MESSAGE =
            "com.example.android.RecipeBook.extra.MESSAGE";

    private SwipeRefreshLayout swipeRefresh;

    private CategoryListAdapter categoryListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvCategory = findViewById(R.id.rv_category);

        progressBar = findViewById(R.id.progress_loader);

        swipeRefresh =  findViewById(R.id.container);
        swipeRefresh.setOnRefreshListener(this);

        list = new ArrayList<>();

        rvCategory.setLayoutManager(new GridLayoutManager(this, 2));
         categoryListAdapter = new CategoryListAdapter(this);
        categoryListAdapter.setListCategory(list);
        rvCategory.setAdapter(categoryListAdapter);

        getData(progressBar, categoryListAdapter);

        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelected(list.get(position));
            }
        });
    }

    private void showSelected(Categories categories) {
        Intent intent = new Intent(this, MealActivity.class);
        intent.putExtra(EXTRA_MESSAGE, categories.getStrCategory());
        startActivity(intent);
    }

    private void getData(ProgressBar progressBar, CategoryListAdapter categoryListAdapter){
        new FetchCategoryBook(list, progressBar , categoryListAdapter).execute();
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                swipeRefresh.setRefreshing(false);


                getData(progressBar, categoryListAdapter);

            }
        },2000);
    }
}

