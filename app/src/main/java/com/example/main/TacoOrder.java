package com.example.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class TacoOrder extends AppCompatActivity {
    RadioGroup sizeGroup, tortillaGroup;
    Button placeOrderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taco_order);

        placeOrderButton = (Button) findViewById(R.id.place_order_button);
        sizeGroup = (RadioGroup) findViewById(R.id.size_group);
        tortillaGroup = (RadioGroup) findViewById(R.id.tortilla_group);

        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton selectedSize = (RadioButton) findViewById(sizeGroup.getCheckedRadioButtonId());
                String size = selectedSize.getText().toString();
            }
        });
    }
}