package com.example.recipebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.recipebook.Model.Meal;
import com.example.recipebook.adapter.MealListAdapter;
import com.example.recipebook.async.FetchMeal;

import java.util.ArrayList;

public class MealActivity extends AppCompatActivity {
    private RecyclerView rvCategory;

    private ProgressBar progressBar;

    private ArrayList<Meal> list;

    public static final String EXTRA_MESSAGE =
            "com.example.android.RecipeBook.extra.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String strCateogry = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(strCateogry);
        }

        rvCategory = findViewById(R.id.rv_category);

        progressBar = findViewById(R.id.progress_loader);

        list = new ArrayList<>();

        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        MealListAdapter mealListAdapter = new MealListAdapter(this);
        mealListAdapter.setListMeal(list);
        rvCategory.setAdapter(mealListAdapter);

        new FetchMeal(list, progressBar , mealListAdapter).execute("filter.php", "c", strCateogry);

        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelected(list.get(position));
            }
        });
    }

    private void showSelected(Meal meals) {
        Intent moveWithObjectIntent = new Intent(this, RecipeAcitivity.class);
        moveWithObjectIntent.putExtra(EXTRA_MESSAGE, meals);
        startActivity(moveWithObjectIntent);

    }
    }

