package com.test.recipezip.model;

import java.util.Objects;

public class Ingredient {
//    public String foodId;
//    public float quantity;
//    public Measure measure;
//    public float weight;
//    public Food food;
//    public String foodCategory;
      public String text;
      public String weight;
      public String image ="https://www.edamam.com/web-img/ccf/ccffc8a98f443012071e4bb3f33bdf3e.jpg";
      public String calories = "unknown";
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(text, that.text) &&
                Objects.equals(weight, that.weight) &&
                Objects.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, weight, image);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "text='" + text + '\'' +
                ", weight='" + weight + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public Ingredient(String text, String weight, String calories) {
        this.text = text;
        this.weight = weight;
        if(calories != null && calories.length() != 0) {
            this.calories = calories;
        }
    }
}
