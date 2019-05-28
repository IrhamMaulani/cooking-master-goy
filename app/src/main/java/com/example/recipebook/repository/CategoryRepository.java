package com.example.recipebook.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.recipebook.dao.CategoryDao;
import com.example.recipebook.database.RecipeDatabase;
import com.example.recipebook.model.Category;

import java.util.List;

public class CategoryRepository {

    private CategoryDao categoryDao;
    private LiveData<List<Category>> allCategory;
    private Category anyCategory;

    public CategoryRepository(Application application) {
        RecipeDatabase db = RecipeDatabase.getDatabase(application);
        categoryDao = db.categoryDao();
        allCategory = categoryDao.getAllCategory();

    }

    public LiveData<List<Category>> getAllCategory() {
        return allCategory;
    }



    public void insert (Category category) {
        new insertAsyncTask(categoryDao).execute(category);
    }

    private static class insertAsyncTask extends AsyncTask<Category, Void, Void> {

        private CategoryDao mAsyncTaskDao;

        insertAsyncTask(CategoryDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Category... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
