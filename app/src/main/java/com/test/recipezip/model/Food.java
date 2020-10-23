package com.test.recipezip.model;

import java.util.Objects;

public class Food {
    String foodId;
    String label;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return Objects.equals(foodId, food.foodId) &&
                Objects.equals(label, food.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foodId, label);
    }

    @Override
    public String toString() {
        return "Food{" +
                "foodId='" + foodId + '\'' +
                ", label='" + label + '\'' +
                '}';
    }
}
