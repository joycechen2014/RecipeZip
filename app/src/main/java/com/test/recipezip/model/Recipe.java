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
    public boolean favorite;

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
    public Recipe(String str) {
        str = str.substring(1, str.length()-1);
        String[] strings = str.split(",");
        uri = strings[0].split("=")[1];
        uri = uri.substring(1, uri.length()-1);
        label = strings[1].split("=")[1];
        label = label.substring(1, label.length()-1);
        image = strings[2].split("=")[1];

        image = image.substring(1, image.length()-1);
        source = strings[3].split("=")[1];

        source = source.substring(1, source.length()-1);
        url = strings[4].split("=")[1];
        url = url.substring(1, url.length()-1);
        //yield = Integer.parseInt(strings[5].split("=")[1].substring(1));
        //calories = Float.parseFloat(strings[6].split("=")[1].substring(1));
        //totalWeight = Float.parseFloat(strings[7].split("=")[1].substring(1));
    }
}
