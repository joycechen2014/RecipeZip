package com.test.recipezip.model;

import java.util.Objects;

public class Nutrients {
    String label;
    String quantity;
    String unit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nutrients nutrients = (Nutrients) o;
        return Objects.equals(label, nutrients.label) &&
                Objects.equals(quantity, nutrients.quantity) &&
                Objects.equals(unit, nutrients.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, quantity, unit);
    }

    @Override
    public String toString() {
        return "Nutrients{" +
                "label='" + label + '\'' +
                ", quantity='" + quantity + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }
}
