package com.example.main;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CookingBookActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cooking_book_activity);

        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        recipes.add(new Recipe("Broccoli Cheese Soup", R.drawable.broccoli_cheese_soup, "https://www.gimmesomeoven.com/broccoli-cheese-soup-recipe/"));
        recipes.add(new Recipe("Rosemary Chicken Noodle Soup", R.drawable.rosemary_chicken_noodle_soup, "https://www.gimmesomeoven.com/rosemary-chicken-noodle-soup-recipe/"));
        recipes.add(new Recipe("Rustic Potato Leek Soup", R.drawable.rustic_potato_leek_soup, "https://www.gimmesomeoven.com/rustic-potato-leek-soup/"));

        CookingBookAdapter adapter = new CookingBookAdapter(this, recipes);

        ListView listView = findViewById(R.id.cooking_book_listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(this, "Tapped " + recipes.get(position).name, Toast.LENGTH_SHORT).show();
        });
    }

}