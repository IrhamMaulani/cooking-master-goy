package com.example.recipebook.async;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.example.recipebook.Model.Categories;
import com.example.recipebook.Model.Meal;
import com.example.recipebook.adapter.MealListAdapter;
import com.example.recipebook.network.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class FetchMeal extends AsyncTask<String, Integer, String> {

    private MealListAdapter mealListAdapter;

    private ArrayList<Meal> mealses;

    private WeakReference<ProgressBar> progressBar;


    public FetchMeal(ArrayList<Meal> mealses, ProgressBar progressBar, MealListAdapter mealListAdapter) {
        this.mealListAdapter = mealListAdapter;
        this.progressBar = new WeakReference<>(progressBar);
        this.mealses = mealses;
    }

    @Override
    protected String  doInBackground(String... strings) {
        return NetworkUtils.getDataUseParameter(strings[0], strings[1], strings[2]);
    }

    @Override
    protected void onPostExecute(String s) {
        progressBar.get().setVisibility(View.INVISIBLE);

        try {
            // Convert the response into a JSON object.
            JSONObject jsonObject = new JSONObject(s);
            // Get the JSONArray of book items.
            JSONArray itemsArray = jsonObject.getJSONArray("meals");

            for (int i = 0; i < itemsArray.length(); i++) {
                JSONObject meal = itemsArray.getJSONObject(i);
                Meal meals = new Meal(meal);
                mealses.add(meals);
            }

            mealListAdapter.setListMeal(mealses);
            mealListAdapter.notifyDataSetChanged();

        }catch (Exception e) {

            e.printStackTrace();
        }
    }
}
