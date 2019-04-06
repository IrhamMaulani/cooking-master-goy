package com.example.recipebook.async;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.recipebook.Model.Categories;
import com.example.recipebook.Model.Meal;
import com.example.recipebook.Model.Recipe;
import com.example.recipebook.R;
import com.example.recipebook.adapter.MealListAdapter;
import com.example.recipebook.network.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class FetchRecipe extends AsyncTask<String, Integer, String> {

    private WeakReference<TextView> tvTags, tvArea, tvIngredients,tvMeasure, tvInstruction, tvEquivalent ;
    private WeakReference<ImageView> ivYoutube;
    private Context context;


    public FetchRecipe(TextView tvEquivalent,TextView tvTags, TextView tvArea, TextView tvIngredients, TextView tvMeasure, TextView tvInstruction, ImageView ivYoutube, Context context) {
       this.tvEquivalent = new WeakReference<>(tvEquivalent);
        this.tvTags =  new WeakReference<>(tvTags);
        this.tvArea =  new WeakReference<>(tvArea);
        this.tvIngredients =  new WeakReference<>(tvIngredients);
        this.tvMeasure =  new WeakReference<>(tvMeasure);
        this.tvInstruction =  new WeakReference<>(tvInstruction);
        this.ivYoutube = new WeakReference<>(ivYoutube);
        this.context = context;
    }

    @Override
    protected String  doInBackground(String... strings) {
        return NetworkUtils.getDataUseParameter(strings[0], strings[1], strings[2]);
    }

    @Override
    protected void onPostExecute(String s) {

        try {
            // Convert the response into a JSON object.
            JSONObject jsonObject = new JSONObject(s);
            // Get the JSONArray of book items.
            JSONArray itemsArray = jsonObject.getJSONArray("meals");



            // Initialize iterator and results fields.
            String tags = null;
            String area = null;
            Recipe recipes = null;

            // Look for results in the items array, exiting when both the
            // title and author are found or when all items have been checked.

            for (int i = 0; i < itemsArray.length(); i++) {
                JSONObject recipe = itemsArray.getJSONObject(i);
                recipes = new Recipe(recipe);
            }

            if(recipes != null){
                if(!recipes.getStrTags().equals("null")){
                    tvTags.get().setText(String.format("Tags : %s", recipes.getStrTags()));
                }else{
                    tvTags.get().setText("Tags :  ");
                }

                tvArea.get().setText(String.format("From : %s", recipes.getStrArea()));

                tvInstruction.get().setText(recipes.getStrInstructions());

                final Recipe finalRecipes = recipes;


                if(finalRecipes.getStrYoutube().equals("")){
                    ivYoutube.get().setVisibility(View.GONE);
                }else{
                    ivYoutube.get().setVisibility(View.VISIBLE);
                }

                ivYoutube.get().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(finalRecipes.getStrYoutube())));
                    }
                });

                for (int i = 0; i < 20; i++) {
                    if(i == 0){
                        i++;
                    }
                    tvEquivalent.get().append("="  +"\n" );
                    tvIngredients.get().append("- " + itemsArray.getJSONObject(0).getString("strIngredient" + i) + "\n" );
                    tvMeasure.get().append(itemsArray.getJSONObject(0).getString("strMeasure1" + i) + "\n" );
                }


            }



        } catch (Exception e) {
            // If onPostExecute() does not receive a proper JSON string,
            // update the UI to show failed results.

            e.printStackTrace();
        }
    }
}
