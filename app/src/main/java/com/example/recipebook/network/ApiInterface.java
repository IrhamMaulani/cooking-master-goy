package com.example.recipebook.network;

import com.example.recipebook.model.CategoryResponse;
import com.example.recipebook.model.MealResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("categories.php")
    Call<CategoryResponse> getCategoriesList();

    @GET("filter.php")
    Call<MealResponse> getMealList(@Query("c") String category);

}
