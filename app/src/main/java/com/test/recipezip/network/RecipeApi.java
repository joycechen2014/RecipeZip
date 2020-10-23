package com.test.recipezip.network;

import com.test.recipezip.model.Hits;
import com.test.recipezip.model.RecipeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipeApi {
    @GET("search")
    Call<RecipeResponse> getEverything(
            @Query("q") String query,@Query("app_id") String app_id,@Query("app_key") String app_key,@Query("ingr") int ingr);

}
