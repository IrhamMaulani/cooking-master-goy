package com.example.recipebook.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class Meal implements Parcelable {

    private String strMeal,strMealThumb,idMeal;


    public Meal(JSONObject object){
        try {
            String strMeal = object.getString("strMeal");
            String strMealThumb = object.getString("strMealThumb");
            String idMeal = object.getString("idMeal");
            this.strMeal = strMeal;
            this.strMealThumb = strMealThumb;
            this.idMeal = idMeal;

        }catch (Exception e){
            e.printStackTrace();
        }
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

    protected Meal(Parcel in) {
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
