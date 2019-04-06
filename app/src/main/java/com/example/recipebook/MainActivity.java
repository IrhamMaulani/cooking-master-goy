package com.example.recipebook;

import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvCategory;

    private ProgressBar progressBar;

    private ArrayList<Categories> list;

    public static final String EXTRA_MESSAGE =
            "com.example.android.RecipeBook.extra.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvCategory = findViewById(R.id.rv_category);

        progressBar = findViewById(R.id.progress_loader);

        list = new ArrayList<>();

        rvCategory.setLayoutManager(new GridLayoutManager(this, 2));
        CategoryListAdapter categoryListAdapter = new CategoryListAdapter(this);
        categoryListAdapter.setListCategory(list);
        rvCategory.setAdapter(categoryListAdapter);

        new FetchCategoryBook(list, progressBar , categoryListAdapter).execute();

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
    }

