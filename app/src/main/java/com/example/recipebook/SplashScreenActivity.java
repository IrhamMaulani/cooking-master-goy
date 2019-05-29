package com.example.recipebook;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.recipebook.dao.CategoryDao;
import com.example.recipebook.model.Category;
import com.example.recipebook.model.CategoryResponse;
import com.example.recipebook.network.ApiClient;
import com.example.recipebook.network.ApiInterface;
import com.example.recipebook.preference.AppPreference;
import com.example.recipebook.viewmodel.CategoryViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends AppCompatActivity {

    private ArrayList<Category> list;

    ApiInterface apiInterface;

     CategoryViewModel categoryViewModel;
    AppPreference appPreference;

    @BindView(R.id.main_layout)
    ConstraintLayout mainLayout;

    private Snackbar snackbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ButterKnife.bind(this);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        list = new ArrayList<>();

        categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);

         appPreference =  new AppPreference(this);

        Boolean firstRun = appPreference.getFirstRun();

        snackbar = Snackbar.make(mainLayout, R.string.check_your_connection, Snackbar.LENGTH_INDEFINITE);

         if(firstRun) {
             getData();
         } else{
            Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
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
                    insertData(list);
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                snackbar.show();
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

    private void insertData(ArrayList<Category> categories) {

            try {
                for (int i = 1; i < categories.size(); i++) {

                    categoryViewModel.insert(categories.get(i));
                }

                appPreference.setFirstRun(false);

                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            } catch (Exception e) {
                Log.d("ERROR", "" + e);
            }
        }


    }

