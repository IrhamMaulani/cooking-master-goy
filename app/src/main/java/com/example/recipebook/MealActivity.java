package com.example.recipebook;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.recipebook.dao.MealDao;
import com.example.recipebook.model.Category;
import com.example.recipebook.model.CategoryResponse;
import com.example.recipebook.model.Meal;
import com.example.recipebook.adapter.MealListAdapter;
import com.example.recipebook.model.MealResponse;
import com.example.recipebook.network.ApiClient;
import com.example.recipebook.network.ApiInterface;
import com.example.recipebook.viewmodel.CategoryViewModel;
import com.example.recipebook.viewmodel.MealViewModel;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealActivity extends AppCompatActivity {
    private RecyclerView rvCategory;

    private ProgressBar progressBar;

    ApiInterface apiInterface;

    private ArrayList<Meal> list;

    public static final String EXTRA_MESSAGE =
            "com.example.android.RecipeBook.extra.MESSAGE";

    MealViewModel mealViewModel;

    String strCategory;

    private Snackbar snackbar;

    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Intent intent = getIntent();
         strCategory = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(strCategory);
        }

        constraintLayout = findViewById(R.id.container);

        rvCategory = findViewById(R.id.rv_category);

        progressBar = findViewById(R.id.progress_loader);

        progressBar.setVisibility(View.INVISIBLE);

        list = new ArrayList<>();

        snackbar = Snackbar.make(constraintLayout, R.string.check_your_connection, Snackbar.LENGTH_INDEFINITE);

        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        final MealListAdapter mealListAdapter = new MealListAdapter(this);
        mealListAdapter.setListMeal(list);
        rvCategory.setAdapter(mealListAdapter);

        getData(strCategory);

        mealViewModel = ViewModelProviders.of(this).get(MealViewModel.class);

        mealViewModel.getAllCategory(strCategory).observe(this, new Observer<List<Meal>>() {
            @Override
            public void onChanged(@Nullable final List<Meal> meals) {
                // Update the cached copy of the words in the adapter.

                mealListAdapter.setMeal((ArrayList<Meal>) meals);
                list = (ArrayList<Meal>) meals;
            }
        });

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

    public void getData(String strCategory){

        Call<MealResponse> call = apiInterface.getMealList(strCategory);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse>
                    response) {

                if (response.isSuccessful()) {
                    snackbar.dismiss();
                    list = (ArrayList<Meal>) response.body().getMeal();
                    insertData(list);
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                snackbar.show();
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

    private void insertData(ArrayList<Meal> meals) {
        try {
            for (int i = 0; i < meals.size(); i++) {
                meals.get(i).setStrCategory(strCategory);
                mealViewModel.insert(meals.get(i));
            }

        } catch (Exception e) {
            Log.d("ERROR", "" + e);
        }
    }

    @Override
    public void onBackPressed() {
//        result.clear();
        super.onBackPressed();
    }
}

