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
      public String image;

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
}
