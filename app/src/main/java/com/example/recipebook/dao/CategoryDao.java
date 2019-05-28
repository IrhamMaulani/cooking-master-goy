package com.example.recipebook.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.recipebook.model.Category;
import com.example.recipebook.model.Recipe;

import java.util.List;

@Dao
public interface CategoryDao {

    @Insert
    void insert(Category category);

    @Query("DELETE FROM category_table")
    void deleteAll();

    @Query("SELECT * from category_table ORDER BY idCategory ASC")
    LiveData<List<Category>> getAllCategory();



    @Query("SELECT * FROM category_table LIMIT 1")
    Category getAnyCategory();

}
