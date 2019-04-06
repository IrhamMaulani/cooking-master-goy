package com.example.recipebook.Model;

import org.json.JSONObject;

public class Recipe  {

    private String strArea, strInstructions, strTags, strYoutube, strSource;

    public Recipe(JSONObject object){
        try {
            String strArea = object.getString("strArea");
            String strInstructions = object.getString("strInstructions");
            String strTags = object.getString("strTags");
            String strYoutube = object.getString("strYoutube");
            String strSource = object.getString("strSource");
            this.strArea = strArea;
            this.strInstructions = strInstructions;
            this.strTags = strTags;
            this.strYoutube = strYoutube;
            this.strSource = strSource;

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    public void setStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
    }

    public String getStrTags() {
        return strTags;
    }

    public void setStrTags(String strTags) {
        this.strTags = strTags;
    }

    public String getStrYoutube() {
        return strYoutube;
    }

    public void setStrYoutube(String strYoutube) {
        this.strYoutube = strYoutube;
    }

    public String getStrSource() {
        return strSource;
    }

    public void setStrSource(String strSource) {
        this.strSource = strSource;
    }
}
