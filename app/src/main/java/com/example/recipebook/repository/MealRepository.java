package com.example.recipebook.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.example.recipebook.dao.MealDao;
import com.example.recipebook.database.RecipeDatabase;
import com.example.recipebook.model.Category;
import com.example.recipebook.model.Meal;

import java.util.List;

public class MealRepository {

    private MealDao mealDao;
    private LiveData<List<Meal>> allMeal;
    private Category anyCategory;
    private String strCategory;
    private RecipeDatabase db;

    public MealRepository(Application application) {
        this.db = RecipeDatabase.getDatabase(application);

    }


    public LiveData<List<Meal>> getAllMeal(String strCategory) {
        mealDao = db.mealDao();
        allMeal = mealDao.getAllMeal(strCategory);
        return allMeal;
    }


    public void insert (Meal meal) {
        new insertAsyncTask(mealDao).execute(meal);
    }

    private static class insertAsyncTask extends AsyncTask<Meal, Void, Void> {

        private MealDao mAsyncTaskDao;

        insertAsyncTask(MealDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Meal... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
