package com.example.recipebook;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
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

    ApiInterface apiInterface;

    public static final String EXTRA_MESSAGE =
            "com.example.android.RecipeBook.extra.MESSAGE";

    @BindView(R.id.container)
     SwipeRefreshLayout swipeRefresh;

    private CategoryListAdapter categoryListAdapter;

    private Snackbar snackbar;

    private CategoryViewModel categoryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);


//        swipeRefresh.setOnRefreshListener(this);

        snackbar = Snackbar.make(swipeRefresh, R.string.check_your_connection, Snackbar.LENGTH_INDEFINITE);

        list = new ArrayList<>();

        rvCategory.setLayoutManager(new GridLayoutManager(this, 2));
        categoryListAdapter = new CategoryListAdapter(this);
        categoryListAdapter.setListCategory(list);
        rvCategory.setAdapter(categoryListAdapter);

        categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);

        getData();

        categoryViewModel.getAllCategory().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(@Nullable final List<Category> words) {
                // Update the cached copy of the words in the adapter.
                categoryListAdapter.setCategory((ArrayList<Category>) words);
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

  public void getData(){

      Call<CategoryResponse> call = apiInterface.getCategoriesList();
      call.enqueue(new Callback<CategoryResponse>() {
          @Override
          public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse>
                  response) {


              if (response.isSuccessful()) {
                  snackbar.dismiss();
                  list = (ArrayList<Category>) response.body().getCategory();
                  generateData(list);
              }
          }

          @Override
          public void onFailure(Call<CategoryResponse> call, Throwable t) {
              snackbar.show();
              Log.e("Retrofit Get", t.toString());
          }
      });
  }

    private void generateData(ArrayList<Category> categories) {
//        categoryListAdapter.setListCategory(categories);
//        categoryListAdapter.notifyDataSetChanged();
//        progressBar.setVisibility(View.INVISIBLE);

        int z = 1;

        if(z > 2){
            for (int i = 1 ; i < categories.size(); i++){
                categoryViewModel.insert(categories.get(i));
            }
        }

    }


//    @Override
//    public void onRefresh() {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//                swipeRefresh.setRefreshing(false);
//
//                getData();
//
//            }
//        },2000);
//    }
}

