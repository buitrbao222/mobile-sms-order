package com.example.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class RecipeDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView recipeDetailsWebView = new WebView(this);
        setContentView(recipeDetailsWebView);

        String url = getIntent().getStringExtra("url");
        recipeDetailsWebView.loadUrl(url);
//        WebView recipeDetailsWebView = findViewById(R.id.recipe_details_webview);
//        recipeDetailsWebView.loadUrl(url);
    }
}