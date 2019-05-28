package com.example.recipebook.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.recipebook.model.Category;
import com.example.recipebook.model.Meal;
import com.example.recipebook.repository.MealRepository;

import java.util.List;

public class MealViewModel extends AndroidViewModel {

    private MealRepository mealRepository;

    private LiveData<List<Meal>> allMeal;

    private Category anyCategory;

    private String strCategory;



    public MealViewModel(Application application) {
        super(application);

        mealRepository = new MealRepository(application);
        allMeal = mealRepository.getAllMeal();

    }



    public LiveData<List<Meal>> getAllCategory() {
        return allMeal; }


    public void insert(Meal meal) { mealRepository.insert(meal); }



}
