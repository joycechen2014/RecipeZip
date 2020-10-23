package com.test.recipezip.model;

public class Hit {
    public Recipe recipe;
    public boolean bookmarked;
    public boolean bought;

    @Override
    public String toString() {
        return "Hit{" +
                "recipe=" + recipe +
                ", bookmarked=" + bookmarked +
                ", bought=" + bought +
                '}';
    }
}
