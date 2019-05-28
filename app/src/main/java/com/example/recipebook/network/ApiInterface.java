package com.example.recipebook.network;

import com.example.recipebook.model.CategoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("categories.php")
    Call<CategoryResponse> getCategoriesList();

}
