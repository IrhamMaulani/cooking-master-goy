package com.example.recipebook.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;


@Entity(tableName = "category_table")
public class Category {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "idCategory")
    @SerializedName("idCategory")
    private String idCategory;

    @ColumnInfo(name = "strCategory")
    @SerializedName("strCategory")
    private String strCategory;


    @ColumnInfo(name = "strCategoryThumb")
    @SerializedName("strCategoryThumb")
    private String strCategoryThumb;


    @ColumnInfo(name = "strCategoryDescription")
    @SerializedName("strCategoryDescription")
    private String strCategoryDescription;

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public String getStrCategoryThumb() {
        return strCategoryThumb;
    }

    public void setStrCategoryThumb(String strCategoryThumb) {
        this.strCategoryThumb = strCategoryThumb;
    }

    public String getStrCategoryDescription() {
        return strCategoryDescription;
    }

    public void setStrCategoryDescription(String strCategoryDescription) {
        this.strCategoryDescription = strCategoryDescription;
    }
}
