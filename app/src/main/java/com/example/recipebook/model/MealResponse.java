package com.example.recipebook.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MealResponse {
    @SerializedName("meals")
    private List<Meal> meal ;

    @SerializedName("message")
    private String message ;

    public List<Meal> getMeal() {
        return meal ;
    }

    public void setData(List<Meal> meal) {
        this.meal  = meal;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
