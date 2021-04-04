package com.example.main;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class BaseActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public void launchActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_taco_order:
                launchActivity(TacoOrderActivity.class);
                return true;
            case R.id.menu_item_cooking_book:
                launchActivity(CookingBookActivity.class);
                return true;
            case R.id.menu_item_landmarks:
                launchActivity(LandmarksActivity.class);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}