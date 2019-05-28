package com.example.recipebook;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.recipebook.model.Meal;

public class RecipeAcitivity extends AppCompatActivity {

    ImageView ivFood, ivYoutube;
    TextView tvTags, tvArea, tvIngredients, tvMeasure, tvInstruction, tvEquivalent;
    private Meal meal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_acitivity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        meal = getIntent().getParcelableExtra(MealActivity.EXTRA_MESSAGE);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(meal.getStrMeal());
        }

        ivFood = findViewById(R.id.iv_food);

        Glide.with(this)
                .load(meal.getStrMealThumb())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.loading)
                        .dontAnimate()
                        .fitCenter())
                .into(ivFood);

        tvTags = findViewById(R.id.tv_tags);
        tvArea = findViewById(R.id.tv_area);
        tvIngredients = findViewById(R.id.tv_ingredients);
        tvMeasure = findViewById(R.id.tv_measure);
        tvInstruction = findViewById(R.id.tv_instruction);
        tvEquivalent = findViewById(R.id.tv_equivalent);

        ivYoutube = findViewById(R.id.iv_youtube);

//        new FetchRecipe(tvEquivalent, tvTags, tvArea, tvIngredients, tvMeasure, tvInstruction, ivYoutube, this).execute("lookup.php", "i", meal.getIdMeal());

        AppBarLayout mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true;

                } else if (isShow) {
                    isShow = false;

                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
