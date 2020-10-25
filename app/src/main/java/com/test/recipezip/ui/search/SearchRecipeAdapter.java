package com.test.recipezip.ui.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.test.recipezip.R;
import com.test.recipezip.databinding.SearchRecipeItemBinding;
import com.test.recipezip.model.Hit;
import com.test.recipezip.model.Recipe;

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
//        holder.favoriteImageView.setImageResource(R.drawable.ic_favorite_24dp);
        holder.itemTitleTextView.setText(recipe.label);
        Picasso.get().load(recipe.image).into(holder.itemImageView);
        holder.itemView.setOnClickListener(v -> itemCallback.onOpenDetails(recipe));
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    // 3. SearchNewsViewHolder:
    public class SearchNewsViewHolder extends RecyclerView.ViewHolder {

        private ScaleAnimation scaleAnimation;
        private BounceInterpolator bounceInterpolator;
        ToggleButton toggleButton;
        ImageView itemImageView;
        TextView itemTitleTextView;

        public SearchNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            SearchRecipeItemBinding binding = SearchRecipeItemBinding.bind(itemView);
            toggleButton = binding.searchItemFavorite;
            scaleAnimation = new ScaleAnimation(0.7f, 1.0f, 0.7f, 1.0f, Animation.RELATIVE_TO_SELF, 0.7f, Animation.RELATIVE_TO_SELF, 0.7f);
            scaleAnimation.setDuration(500);
            bounceInterpolator = new BounceInterpolator();
            scaleAnimation.setInterpolator(bounceInterpolator);
            toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    //animation
                    compoundButton.startAnimation(scaleAnimation);
                }});
//            itemImageView = binding.searchItemImage;
            itemImageView = (ImageView) itemView.findViewById(R.id.search_item_image);
            itemImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            itemTitleTextView = binding.searchItemTitle;
        }
    }
}

