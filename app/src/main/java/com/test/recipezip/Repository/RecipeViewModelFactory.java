package com.test.recipezip.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.test.recipezip.ui.search.SearchViewModel;

public class RecipeViewModelFactory implements ViewModelProvider.Factory {
    private final RecipeRepository repository;

    public RecipeViewModelFactory(RecipeRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SearchViewModel.class)) {
            return (T) new SearchViewModel(repository);
        }
     else {
            throw new IllegalStateException("Unknown ViewModel");
        }
    }

}
