package com.test.recipezip.model;

import java.io.Serializable;
import java.util.Arrays;

public class Recipe implements Serializable {
    public String uri;
    public String label;
    public String image;
    public String source;
    public String url;
    public Integer yield;
    public float calories;
    public float totalWeight;
    public Ingredient[] ingredients;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "uri='" + uri + '\'' +
                ", label='" + label + '\'' +
                ", image='" + image + '\'' +
                ", source='" + source + '\'' +
                ", url='" + url + '\'' +
                ", yield=" + yield +
                ", calories=" + calories +
                ", totalWeight=" + totalWeight +
                ", ingredients=" + Arrays.toString(ingredients) +
                '}';
    }
}
