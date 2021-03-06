package com.test.recipezip.ui.favorite;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.test.recipezip.FavoriteHelper;
import com.test.recipezip.MainActivity;
import com.test.recipezip.R;
import com.test.recipezip.Repository.RecipeRepository;
import com.test.recipezip.Repository.RecipeViewModelFactory;
import com.test.recipezip.databinding.FragmentFavoriteBinding;
import com.test.recipezip.databinding.FragmentSearchBinding;
import com.test.recipezip.model.Recipe;
import com.test.recipezip.ui.search.SearchFragment;
import com.test.recipezip.ui.search.SearchFragmentDirections;
import com.test.recipezip.ui.search.SearchRecipeAdapter;
import com.test.recipezip.ui.search.SearchViewModel;

import java.util.List;

public class FavoriteFragment extends Fragment {
    private SearchViewModel viewModel;
    private FragmentFavoriteBinding binding;
    private long userId = -1;
    private FavoriteHelper favoriteHelper = null;


    public FavoriteFragment() {
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
        //return inflater.inflate(R.layout.fragment_favorite, container, false);
        Intent intent = getActivity().getIntent();
        favoriteHelper = new FavoriteHelper(this.getContext());
        userId = intent.getIntExtra("uid", -1);
        Log.d("favorite fragment", "@@@@@@@@@@@@@@@@@@@@@@@@@@@"+userId);
        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Intent intent = getActivity().getIntent();
        long userId = intent.getIntExtra("uid", -1);
        FavoriteRecipeAdapter recipeAdapter = new FavoriteRecipeAdapter(userId, favoriteHelper);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 1);
        binding.newsResultsRecyclerView.setLayoutManager(gridLayoutManager);
        binding.newsResultsRecyclerView.setAdapter(recipeAdapter);


        List<Recipe> recipeList =  favoriteHelper.getAllFavorite(userId);
        recipeList.forEach(recipe -> {recipe.setFavorite(true);});
        Log.d("showing favorite!!!!!!!!!!!!!!", recipeList.toString());
        recipeAdapter.setRecipes(recipeList);
        recipeAdapter.setItemCallback(article -> {
            FavoriteFragmentDirections.ActionNavigationFavoriteToNavigationDetails direction = FavoriteFragmentDirections.actionNavigationFavoriteToNavigationDetails(article);
            NavHostFragment.findNavController(FavoriteFragment.this).navigate(direction);
        });
//

        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get Action from genereted Directions class
                 FavoriteFragmentDirections.ActionNavigationFavoriteToNavigationNewrecipe action = FavoriteFragmentDirections.actionNavigationFavoriteToNavigationNewrecipe(userId);
                 //action

              // FavoriteFragmentAction action = FavoriteFragmentDirections.actionNavigationSearchToNavigationNewrecipe();
                Navigation.findNavController(view).navigate(action);
            }
        });
//        RecipeRepository repository = new RecipeRepository(getContext());
//        viewModel = new ViewModelProvider(this, new RecipeViewModelFactory(repository))
//                .get(SearchViewModel.class);
//
//        viewModel
//                .getEverything()
//                .observe(
//                        getViewLifecycleOwner(),
//                        recipeResponse -> {
//                            if (recipeResponse != null) {
//                                Log.d("SearchFragment",recipeResponse.toString());
//                                for (int i = 0; i < recipeResponse.hits.length; i++) {
//                                    recipeResponse.hits[i].recipe.favorite = favoriteHelper.isFavorite(userId, recipeResponse.hits[i].recipe.toString());
//                                }
//                                recipeAdapter.setArticles(recipeResponse.hits);
//                            }
//                        });


//        recipeAdapter.setItemCallback(article -> {
//            SearchFragmentDirections.ActionNavigationSearchToNavigationDetails direction = SearchFragmentDirections.actionNavigationSearchToNavigationDetails(article);
//            NavHostFragment.findNavController(SearchFragment.this).navigate(direction);
//        });
    }
}