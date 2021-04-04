package com.example.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

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
                launchActivity(TacoOrder.class);
                return true;
            case R.id.menu_item_cooking_book:
                launchActivity(CookingBook.class);
                return true;
            case R.id.menu_item_landmarks:
                launchActivity(Landmarks.class);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
