package com.example.recipebook.async;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recipebook.Model.Recipe;
import com.example.recipebook.network.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class FetchRecipe extends AsyncTask<String, Integer, String> {

    private WeakReference<TextView> tvTags, tvArea, tvIngredients, tvMeasure, tvInstruction, tvEquivalent;
    private WeakReference<ImageView> ivYoutube;
    private WeakReference<Context> context;


    public FetchRecipe(TextView tvEquivalent, TextView tvTags, TextView tvArea, TextView tvIngredients, TextView tvMeasure, TextView tvInstruction, ImageView ivYoutube, Context context) {
        this.tvEquivalent = new WeakReference<>(tvEquivalent);
        this.tvTags = new WeakReference<>(tvTags);
        this.tvArea = new WeakReference<>(tvArea);
        this.tvIngredients = new WeakReference<>(tvIngredients);
        this.tvMeasure = new WeakReference<>(tvMeasure);
        this.tvInstruction = new WeakReference<>(tvInstruction);
        this.ivYoutube = new WeakReference<>(ivYoutube);
        this.context = new WeakReference<>(context);
    }

    @Override
    protected String doInBackground(String... strings) {
        return NetworkUtils.getDataUseParameter(strings[0], strings[1], strings[2]);
    }

    @Override
    protected void onPostExecute(String s) {
        try {
            JSONObject jsonObject = new JSONObject(s);

            JSONArray itemsArray = jsonObject.getJSONArray("meals");

            Recipe recipes = null;

            for (int i = 0; i < itemsArray.length(); i++) {
                JSONObject recipe = itemsArray.getJSONObject(i);
                recipes = new Recipe(recipe);
            }

            if (recipes != null) {
                if (!recipes.getStrTags().equals("null")) {
                    tvTags.get().setText(String.format("Tags : %s", recipes.getStrTags()));
                } else {
                    tvTags.get().setText("Tags :  ");
                }

                tvArea.get().setText(String.format("From : %s", recipes.getStrArea()));

                tvInstruction.get().setText(recipes.getStrInstructions());

                final Recipe finalRecipes = recipes;


                if (finalRecipes.getStrYoutube().equals("")) {
                    ivYoutube.get().setVisibility(View.GONE);
                } else {
                    ivYoutube.get().setVisibility(View.VISIBLE);
                }

                ivYoutube.get().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.get().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(finalRecipes.getStrYoutube())));
                    }
                });

                for (int i = 0; i < 20; i++) {
                    if (i == 0) {
                        i++;
                    }
                    tvEquivalent.get().append("=" + "\n");
                    tvIngredients.get().append("- " + itemsArray.getJSONObject(0).getString("strIngredient" + i) + "\n");
                    tvMeasure.get().append(itemsArray.getJSONObject(0).getString("strMeasure1" + i) + "\n");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
