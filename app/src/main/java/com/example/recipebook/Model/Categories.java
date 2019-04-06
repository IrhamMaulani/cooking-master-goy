package com.example.recipebook.Model;

import org.json.JSONObject;

public class Categories {

    private String idCategory, strCategory, strCategoryThumb, strCategoryDescription;

    public  Categories(JSONObject object){
        try {
            String idCategory = object.getString("idCategory");
            String strCategory = object.getString("strCategory");
            String strCategoryThumb = object.getString("strCategoryThumb");
            String strCategoryDescription =  object.getString("strCategoryDescription");
            this.idCategory = idCategory;
            this.strCategory = strCategory;
            this.strCategoryThumb = strCategoryThumb;
            this.strCategoryDescription = strCategoryDescription;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

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
