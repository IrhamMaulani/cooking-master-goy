package com.example.recipebook.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

@Entity(tableName = "meal_table")
public class Meal implements Parcelable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "idMeal")
    @SerializedName("idMeal")
    private String idMeal;

    @ColumnInfo(name = "strMeal")
    @SerializedName("strMeal")
    private String strMeal;

    @ColumnInfo(name = "strMealThumb")
    @SerializedName("strMealThumb")
    private String strMealThumb;

    @ColumnInfo(name = "strCategory")
    @SerializedName("strCategory")
    private String strCategory;

    public Meal() {

    }



    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.strMeal);
        dest.writeString(this.strMealThumb);
        dest.writeString(this.idMeal);
    }

    public Meal(Parcel in) {
        this.strMeal = in.readString();
        this.strMealThumb = in.readString();
        this.idMeal = in.readString();
    }

    public static final Creator<Meal> CREATOR = new Creator<Meal>() {
        @Override
        public Meal createFromParcel(Parcel source) {
            return new Meal(source);
        }

        @Override
        public Meal[] newArray(int size) {
            return new Meal[size];
        }
    };
}
