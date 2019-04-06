package com.example.recipebook.async;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.example.recipebook.Model.Categories;
import com.example.recipebook.adapter.CategoryListAdapter;
import com.example.recipebook.network.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class FetchCategoryBook extends AsyncTask<String, Integer, String> {

    private ArrayList<Categories> categorieses;

    private CategoryListAdapter categoryListAdapter;

    private WeakReference<ProgressBar> progressBar;

    public FetchCategoryBook(ArrayList<Categories> categories, ProgressBar progressBar, CategoryListAdapter categoryListAdapter) {
        this.categorieses = categories;
        this.categoryListAdapter = categoryListAdapter;
        this.progressBar = new WeakReference<>(progressBar);

    }

    @Override
    protected String  doInBackground(String... strings) {
        return NetworkUtils.getCategory();
    }

    @Override
    protected void onPostExecute(String s) {
        progressBar.get().setVisibility(View.INVISIBLE);
        try {
            // Convert the response into a JSON object.
            JSONObject jsonObject = new JSONObject(s);
            // Get the JSONArray of book items.
            JSONArray itemsArray = jsonObject.getJSONArray("categories");

            for (int i = 0; i < itemsArray.length(); i++) {
                JSONObject category = itemsArray.getJSONObject(i);
                Categories categories = new Categories(category);
                categorieses.add(categories);
            }

            categoryListAdapter.setListCategory(categorieses);
            categoryListAdapter.notifyDataSetChanged();


        }catch (Exception e) {

        e.printStackTrace();
    }
    }
}
