package com.test.recipezip.model;

import java.util.Objects;

public class Ingredient {
    String foodId;
    float quantity;
    Measure measure;
    float weight;
    Food food;
    String foodCategory;

    @Override
    public String toString() {
        return "Ingredient{" +
                "foodId='" + foodId + '\'' +
                ", quantity=" + quantity +
                ", measure=" + measure +
                ", weight=" + weight +
                ", food=" + food +
                ", foodCategory='" + foodCategory + '\'' +
                '}';
    }
}
