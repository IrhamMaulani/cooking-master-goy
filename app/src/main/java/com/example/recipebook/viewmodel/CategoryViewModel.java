package com.example.recipebook.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.recipebook.model.Category;
import com.example.recipebook.repository.CategoryRepository;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {

    private CategoryRepository categoryRepository;

    private LiveData<List<Category>> allCategory;

    public CategoryViewModel (Application application) {
        super(application);
        categoryRepository = new CategoryRepository(application);
        allCategory = categoryRepository.getAllCategory();
    }

    public LiveData<List<Category>> getAllCategory() { return allCategory; }

    public void insert(Category word) { categoryRepository.insert(word); }
}
