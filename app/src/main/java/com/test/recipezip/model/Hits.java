package com.test.recipezip.model;

import java.util.Arrays;
import java.util.List;

public class Hits {
    public String q;
    public Integer from;
    public Integer to;
    public Integer count;
    public boolean more;
    public Hit[] hits;
    public List<Hit> hitList;
    Hits() {
        hitList = Arrays.asList(hits);
    }

    @Override
    public String toString() {
        return "Hits{" +
                "q='" + q + '\'' +
                ", from=" + from +
                ", to=" + to +
                ", count=" + count +
                ", more=" + more +
                ", hits=" + Arrays.toString(hits) +
                '}';
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }
}
