package com.test.recipezip.ui.details;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.test.recipezip.R;
import com.test.recipezip.databinding.SearchIngredientItemBinding;
import com.test.recipezip.model.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class DetailIngredientAdapter extends RecyclerView.Adapter<DetailIngredientAdapter.DetailIngredientViewHolder>  {

    // 1. Supporting data:
    // TODO
    List<Ingredient> ingredientList = new ArrayList<>();
    public void setIngredient(Ingredient[] ingredients) {
        ingredientList.clear();
       // articles.addAll(newsList);
        for(int i = 0;i < ingredients.length;i++) {
            ingredientList.add(ingredients[i]);
        }
        notifyDataSetChanged();
    }


    // 2. Adapter overrides:
    @NonNull
    @Override
    public DetailIngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_ingredient_item, parent, false);
        return new DetailIngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailIngredientViewHolder holder, int position) {
        Ingredient ingredient = ingredientList.get(position);
        holder.itemTitleTextView.setText(ingredient.text);
        holder.itemQuantityTextView.setText(String.valueOf(ingredient.weight));
        Picasso.get().load(ingredient.image).into(holder.itemImageView);
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }
    // 3. DetailIngredient:
    public static class DetailIngredientViewHolder extends RecyclerView.ViewHolder {

//        ImageView favoriteImageView;
//        ImageView itemImageView;
        TextView itemTitleTextView;
        TextView itemQuantityTextView;
        ImageView itemImageView;
        public DetailIngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            SearchIngredientItemBinding binding = SearchIngredientItemBinding.bind(itemView);
            itemTitleTextView = binding.ingredientTitleContent;
         //   itemImageView = binding.searchItemImage;
            itemQuantityTextView = binding.ingredientWeightContent;
            itemImageView = binding.imageView;
        }
    }
}

