package com.test.recipezip.ui.favorite;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.test.recipezip.FavoriteHelper;
import com.test.recipezip.R;
import com.test.recipezip.databinding.FragmentFavoriteBinding;
import com.test.recipezip.databinding.FragmentNewRecipeBinding;
import com.test.recipezip.model.Ingredient;
import com.test.recipezip.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class NewRecipeFragment extends Fragment {
    private FragmentNewRecipeBinding binding;
    private FavoriteHelper favoriteHelper = null;
    private EditText title;
    private EditText ingredient;
    private NumberPicker picker1;
    private NumberPicker picker2;
    private String[] pickerVals;
    private String unit = "LBs" ;
    private String weight = "0";
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNewRecipeBinding.inflate(inflater, container, false);
        favoriteHelper = new FavoriteHelper(getContext());
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Intent intent = getActivity().getIntent();
        Long Id = NewRecipeFragmentArgs.fromBundle(getArguments()).getUid();
        Button submit = binding.submit;
        EditText title = binding.editTextTitle;
        EditText ing = binding.editTextIngredient;
        EditText cal = binding.editTextCalories;
        picker1 = binding.priorityPicker1;
        picker1.setMaxValue(3);
        picker1.setMinValue(0);
        pickerVals = new String[] {"Lbs", "Kg", "cup", "Ounce"};
        picker1.setDisplayedValues(pickerVals);
        picker1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                int valuePicker1 = picker1.getValue();
                unit = pickerVals[valuePicker1];
            }
        });
        Log.d("weight Before",weight.toString());
        picker2 = binding.priorityPicker2;
        picker2.setMaxValue(100);
        picker2.setMinValue(0);
        picker2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                int valuePicker1 = picker2.getValue();
                weight = String.valueOf(valuePicker1);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("weight=======",weight + " " + unit);
                Ingredient ingredient = new Ingredient(ing.getText().toString() + " " + weight + unit,weight + " " + unit,cal.getText().toString());

                List<Ingredient> ls = new ArrayList<>();
                ls.add(ingredient);
                Recipe newRecipe = new Recipe(title.getText().toString(),ls,Float.valueOf(cal.getText().toString()));
                favoriteHelper.addFavorite(Id, newRecipe.toString());
                Navigation.findNavController(view).navigate(R.id.navigation_favorite);
            }
        });
    }
}