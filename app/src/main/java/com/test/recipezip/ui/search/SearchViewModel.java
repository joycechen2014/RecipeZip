package com.test.recipezip.ui.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.test.recipezip.Repository.RecipeRepository;
import com.test.recipezip.model.Hits;
import com.test.recipezip.model.Recipe;
import com.test.recipezip.model.RecipeResponse;

public class SearchViewModel extends ViewModel {
    private final RecipeRepository repository;
    private final MutableLiveData<String> qInput = new MutableLiveData<>();

    public SearchViewModel(RecipeRepository RecipeRepository) {
        this.repository = RecipeRepository;
    }

    public void setCountryInput(String q) {
        qInput.setValue(q);
    }

    public LiveData<RecipeResponse> getEverything() {
        return Transformations.switchMap(qInput, repository::getEverything);
    }
}
