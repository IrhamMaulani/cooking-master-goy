package com.example.recipebook.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.recipebook.model.Meal;

import java.util.List;

@Dao
public interface MealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Meal meal);

    @Query("DELETE FROM meal_table")
    void deleteAll();

    @Query("SELECT * from meal_table WHERE strCategory = :strCategory ")
    LiveData<List<Meal>> getAllMeal(String strCategory);

    @Query("SELECT * FROM meal_table LIMIT 1")
    Meal getAnyCategory();

}
