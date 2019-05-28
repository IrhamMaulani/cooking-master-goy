package com.example.recipebook.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.recipebook.dao.CategoryDao;
import com.example.recipebook.dao.MealDao;
import com.example.recipebook.model.Category;
import com.example.recipebook.model.Meal;

@Database(entities = {Category.class , Meal.class}, version = 2, exportSchema = false)
public abstract class RecipeDatabase extends RoomDatabase {

    public abstract CategoryDao categoryDao();

    public abstract MealDao mealDao();

    private static RecipeDatabase INSTANCE;

    public static RecipeDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RecipeDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RecipeDatabase.class, "recipe_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}
