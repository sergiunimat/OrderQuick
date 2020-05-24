package com.example.orderquick;

import android.graphics.Bitmap;

public class MealModel {

    private int MealId;
    private String MealName;
    private String MealPrice;
    private String MealDescription;
    private String MealCategory;
    private Bitmap mealImg;

    public MealModel(int mealId, String mealName, String mealPrice, String mealDescription, String mealCategory, Bitmap mealImg) {
        MealId = mealId;
        MealName = mealName;
        MealPrice = mealPrice;
        MealDescription = mealDescription;
        MealCategory = mealCategory;
        this.mealImg = mealImg;
    }

    public int getMealId() {
        return MealId;
    }

    public void setMealId(int mealId) {
        MealId = mealId;
    }

    public String getMealName() {
        return MealName;
    }

    public void setMealName(String mealName) {
        MealName = mealName;
    }

    public String getMealPrice() {
        return MealPrice;
    }

    public void setMealPrice(String mealPrice) {
        MealPrice = mealPrice;
    }

    public String getMealDescription() {
        return MealDescription;
    }

    public void setMealDescription(String mealDescription) {
        MealDescription = mealDescription;
    }

    public String getMealCategory() {
        return MealCategory;
    }

    public void setMealCategory(String mealCategory) {
        MealCategory = mealCategory;
    }

    public Bitmap getMealImg() {
        return mealImg;
    }

    public void setMealImg(Bitmap mealImg) {
        this.mealImg = mealImg;
    }
}
