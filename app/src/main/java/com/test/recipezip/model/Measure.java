package com.test.recipezip.model;

import java.util.Objects;

public class Measure {
    String uri;
    String label;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Measure measure = (Measure) o;
        return Objects.equals(uri, measure.uri) &&
                Objects.equals(label, measure.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uri, label);
    }

    @Override
    public String toString() {
        return "Measure{" +
                "uri='" + uri + '\'' +
                ", label='" + label + '\'' +
                '}';
    }
}
