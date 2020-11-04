package com.test.recipezip.ui.search;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.recipezip.FavoriteHelper;
import com.test.recipezip.R;
import com.test.recipezip.Repository.RecipeRepository;
import com.test.recipezip.Repository.RecipeViewModelFactory;
import com.test.recipezip.databinding.FragmentSearchBinding;
import com.test.recipezip.ui.details.DetailsFragment;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment {

    private SearchViewModel viewModel;
    private FragmentSearchBinding binding;
    private int userId = -1;
    private FavoriteHelper favoriteHelper = null;

    public SearchFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_search, container, false);
        Intent intent = getActivity().getIntent();
        favoriteHelper = new FavoriteHelper(this.getContext());
        userId = intent.getIntExtra("uid", -1);
        Log.d("search fragment", "@@@@@@@@@@@@@@@@@@@@@@@@@@@"+userId);
              binding = FragmentSearchBinding.inflate(inflater, container, false);
              return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Intent intent = getActivity().getIntent();
        int userId = intent.getIntExtra("uid", -1);
        SearchRecipeAdapter recipeAdapter = new SearchRecipeAdapter(userId, favoriteHelper);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 1);
        binding.newsResultsRecyclerView.setLayoutManager(gridLayoutManager);
        binding.newsResultsRecyclerView.setAdapter(recipeAdapter);
        
        
        binding.recipeSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!query.isEmpty()) {
                    viewModel.setCountryInput(query);
                }
                binding.recipeSearchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        RecipeRepository repository = new RecipeRepository(getContext());
        viewModel = new ViewModelProvider(this, new RecipeViewModelFactory(repository))
                .get(SearchViewModel.class);

        viewModel
                .getEverything()
                .observe(
                        getViewLifecycleOwner(),
                        recipeResponse -> {
                            if (recipeResponse != null) {
                                Log.d("SearchFragment",recipeResponse.toString());
                                for (int i = 0; i < recipeResponse.hits.length; i++) {
                                    recipeResponse.hits[i].recipe.favorite = favoriteHelper.isFavorite(userId, recipeResponse.hits[i].recipe.toString());
                                }
                                recipeAdapter.setArticles(recipeResponse.hits);
                            }
                        });


        recipeAdapter.setItemCallback(article -> {
            SearchFragmentDirections.ActionNavigationSearchToNavigationDetails direction = SearchFragmentDirections.actionNavigationSearchToNavigationDetails(article);
            NavHostFragment.findNavController(SearchFragment.this).navigate(direction);
        });



    }

}