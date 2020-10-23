package com.test.recipezip.Repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.test.recipezip.model.Hits;
import com.test.recipezip.model.Recipe;
import com.test.recipezip.model.RecipeResponse;
import com.test.recipezip.network.RecipeApi;
import com.test.recipezip.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeRepository {
    private static final String API_ID = "5f5adea8";
    private static final String API_KEY ="90db22d451899254bdaca3ede182a0c9";
    private final RecipeApi recipeApi;

    public RecipeRepository(Context context) {
        recipeApi = RetrofitClient.newInstance(context).create(RecipeApi.class);
    }

    public LiveData<RecipeResponse> getEverything(String q) {
        MutableLiveData<RecipeResponse> getEverythingLiveData = new MutableLiveData<>();
        recipeApi.getEverything(q,API_ID,API_KEY,5)
                .enqueue(new Callback<RecipeResponse>() {

                    @Override
                    public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                        if (response.isSuccessful()) {
                            getEverythingLiveData.setValue(response.body());
                        } else {
                            getEverythingLiveData.setValue(null);
                        }

                    }

                    @Override
                    public void onFailure(Call<RecipeResponse> call, Throwable t) {
                        getEverythingLiveData.setValue(null);
                    }
                });
        return getEverythingLiveData;
    }

}