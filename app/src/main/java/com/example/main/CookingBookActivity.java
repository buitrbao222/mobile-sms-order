package com.example.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;

public class CookingBookActivity extends BaseActivity {
    private static final int INTERNET_PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cooking_book_activity);

        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        recipes.add(new Recipe("Broccoli Cheese Soup", R.drawable.broccoli_cheese_soup, "https://www.gimmesomeoven.com/broccoli-cheese-soup-recipe/"));
        recipes.add(new Recipe("Rosemary Chicken Noodle Soup", R.drawable.rosemary_chicken_noodle_soup, "https://www.gimmesomeoven.com/rosemary-chicken-noodle-soup-recipe/"));
        recipes.add(new Recipe("Rustic Potato Leek Soup", R.drawable.rustic_potato_leek_soup, "https://www.gimmesomeoven.com/rustic-potato-leek-soup/"));

        CookingBookAdapter adapter = new CookingBookAdapter(this, recipes);

        ListView cookingBookListView = findViewById(R.id.cooking_book_listview);
        cookingBookListView.setAdapter(adapter);

        cookingBookListView.setOnItemClickListener((parent, view, position, id) -> {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
                // Internet permission not granted. Request permissions
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, INTERNET_PERMISSION_REQUEST_CODE);
            } else {
                Intent intent = new Intent(this, RecipeDetailsActivity.class);
                intent.putExtra("url", recipes.get(position).url);
                startActivity(intent);
            }
        });
    }
}