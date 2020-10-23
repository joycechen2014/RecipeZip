package com.test.recipezip.ui.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.test.recipezip.R;
import com.test.recipezip.databinding.SearchRecipeItemBinding;
import com.test.recipezip.model.Hit;
import com.test.recipezip.model.Recipe;
import com.test.recipezip.model.RecipeResponse;

import java.util.ArrayList;
import java.util.List;

public class SearchRecipeAdapter extends RecyclerView.Adapter<SearchRecipeAdapter.SearchNewsViewHolder> {
    // 1. Supporting data:
    private List<Recipe> recipes = new ArrayList<>();


    interface ItemCallback {
        void onOpenDetails(Recipe recipe);
    }

    private ItemCallback itemCallback;

    public void setItemCallback(ItemCallback itemCallback) {
        this.itemCallback = itemCallback;
    }


    public void setArticles(Hit[] recipesList) {
        recipes.clear();
        for(Hit ls : recipesList) {
            recipes.add(ls.recipe);
        }
       // recipes.addAll(recipesList);
        notifyDataSetChanged();
    }


    // 2. Adapter overrides:
    @NonNull
    @Override
    public SearchNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_recipe_item, parent, false);
        return new SearchNewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchNewsViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        holder.favoriteImageView.setImageResource(R.drawable.ic_favorite_24dp);
        holder.itemTitleTextView.setText(recipe.label);
        Picasso.get().load(recipe.image).into(holder.itemImageView);
        holder.itemView.setOnClickListener(v -> itemCallback.onOpenDetails(recipe));
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    // 3. SearchNewsViewHolder:
    public static class SearchNewsViewHolder extends RecyclerView.ViewHolder {

        ImageView favoriteImageView;
        ImageView itemImageView;
        TextView itemTitleTextView;

        public SearchNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            SearchRecipeItemBinding binding = SearchRecipeItemBinding.bind(itemView);
            favoriteImageView = binding.searchItemFavorite;
            itemImageView = binding.searchItemImage;
            itemTitleTextView = binding.searchItemTitle;
        }
    }
}

