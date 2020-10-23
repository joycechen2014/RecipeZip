package com.test.recipezip.model;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class RecipeResponse {
    public String q;
    public Integer from;
    public Integer to;
    public Integer count;
    public boolean more;
    public Hit[] hits;

    @Override
    public String toString() {
        return "RecipeResponse{" +
                "q='" + q + '\'' +
                ", from=" + from +
                ", to=" + to +
                ", count=" + count +
                ", more=" + more +
                ", hits=" + Arrays.toString(hits) +
                '}';
    }
}
