package com.example.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CookingBookAdapter extends ArrayAdapter<Recipe> {
    public CookingBookAdapter(Context context, ArrayList<Recipe> recipes) {
        super(context, 0, recipes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Recipe recipe = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cooking_book_row, parent, false);
        }

        ImageView recipeThumbnailView = convertView.findViewById(R.id.recipe_image);
        recipeThumbnailView.setImageResource(recipe.thumbnail);

        TextView recipeNameView = convertView.findViewById(R.id.recipe_name);
        recipeNameView.setText(recipe.name);

        return convertView;
    }
}
