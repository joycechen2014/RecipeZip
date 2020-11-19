package com.test.recipezip.model;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Recipe implements Serializable {
    public String uri;
    public String label;
    public String image ="https://www.edamam.com/web-img/ccf/ccffc8a98f443012071e4bb3f33bdf3e.jpg";
    public String source;
    public String url;
    public Integer yield;
    public float calories;
    public float totalWeight;
    public Ingredient[] ingredients;
    public boolean favorite;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        ExclusionStrategy strategy = new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes field) {
                if (field.getName().equals("favorite")) {
                    return true;
                }
                return false;
            }
            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        };
        Gson gson = new GsonBuilder().addSerializationExclusionStrategy(strategy).create();
        String json = gson.toJson(this);
        return json;
    }
    public Recipe(String str) {
        Gson gson = new Gson();

        Recipe recipe = gson.fromJson(str, Recipe.class);
        this.uri = recipe.uri;
        this.label = recipe.label;
        this.image = recipe.image;
        this.source = recipe.source;
        this.url = recipe.url;
        this.yield= recipe.yield;
        this.calories = recipe.calories;
        this.totalWeight = recipe.totalWeight;
        this.ingredients = recipe.ingredients;
    }

    public Recipe(String label, List<Ingredient> ingredients,Float calories) {
        this.label = label;
        this.ingredients = new Ingredient[ingredients.size()];
        for(int i = 0;i < ingredients.size();i++) {
            this.ingredients[i] = ingredients.get(i);
        }
        this.calories = calories;

    }
    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}


