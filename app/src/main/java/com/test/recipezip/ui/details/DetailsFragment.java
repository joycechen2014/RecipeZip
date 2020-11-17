package com.test.recipezip.ui.details;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;
import com.squareup.picasso.Picasso;
import com.test.recipezip.R;
import com.test.recipezip.databinding.FragmentDetailsBinding;
import com.test.recipezip.model.Recipe;


public class DetailsFragment extends Fragment {
    private FragmentDetailsBinding binding;
    Button shareButton;
    CallbackManager callbackManager;
    ShareDialog shareDialog;

    public DetailsFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
                binding = FragmentDetailsBinding.inflate(inflater, container, false);
                return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Recipe recipe = DetailsFragmentArgs.fromBundle(getArguments()).getRecipe();
        binding.detailsTitleTextView.setText(recipe.label);
        binding.detailsAuthorTextView.setText(recipe.source);
      //  binding.detailsDateTextView.setText(recipe.yield);
        binding.detailsDescriptionTextView.setText(String.valueOf(recipe.calories));
        //binding.detailsContentTextView.setText(recipe.ingredients.toString());
        Picasso.get().load(recipe.image).into(binding.detailsImageView);
        shareButton = binding.detailsShareButton;
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareLinkContent linkContent = new ShareLinkContent.Builder()
                        .setQuote("I found a good recipe on RecipeZip!")
                        .setContentUrl(Uri.parse(recipe.url))
                        .build();
                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    shareDialog.show(linkContent);
                }
            }
        });
        DetailIngredientAdapter newsAdapter = new DetailIngredientAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 1);
        binding.ingredientRecyclerView.setLayoutManager(gridLayoutManager);
        binding.ingredientRecyclerView.setAdapter(newsAdapter);
        Log.d("ingredient",recipe.ingredients.toString());
        newsAdapter.setIngredient(recipe.ingredients);

    }
}