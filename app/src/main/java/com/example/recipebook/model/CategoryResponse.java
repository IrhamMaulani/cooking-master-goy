package com.example.recipebook.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryResponse {

    @SerializedName("categories")
    private List<Category> category ;

    @SerializedName("message")
    private String message ;

    public List<Category> getCategory() {
        return category ;
    }

    public void setData(List<Category> category) {
        this.category  = category;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
