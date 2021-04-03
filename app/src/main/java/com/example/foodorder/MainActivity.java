package com.example.foodorder;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    protected void addCheckboxes(@IdRes int checkboxGroupId, String[] texts) {
        RadioGroup radioGroup = (RadioGroup) findViewById(checkboxGroupId);

        for (String text : texts) {
            RadioButton radio = new RadioButton(this);

            radio.setText(text);
            radio.setLayoutParams(new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            radioGroup.addView(radio);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}