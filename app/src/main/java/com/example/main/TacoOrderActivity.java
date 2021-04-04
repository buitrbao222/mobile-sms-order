package com.example.main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Gravity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;

public class TacoOrderActivity extends BaseActivity {
    private static final int SMS_PERMISSION_REQUEST_CODE = 1;

    protected String getCheckedRadioText(@IdRes int radioGroupId) {
        RadioGroup radioGroup = findViewById(radioGroupId);
        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton checkedRadioButton = findViewById(checkedRadioButtonId);
        return checkedRadioButton.getText().toString();
    }

    protected ArrayList<String> getCheckedCheckboxText(@IdRes int linearLayoutId) {
        LinearLayout linearLayout = findViewById(linearLayoutId);

        ArrayList<String> texts = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            LinearLayout ll = (LinearLayout) linearLayout.getChildAt(i);

            final int childCount = ll.getChildCount();
            for (int j = 0; j < childCount; j++) {
                CheckBox checkbox = (CheckBox) ll.getChildAt(j);
                if (checkbox.isChecked()) {
                    texts.add(checkbox.getText().toString());
                }
            }
        }

        return texts;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("UnlocalizedSms")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taco_order_activity);

        Button placeOrderButton = findViewById(R.id.place_order_button);

        placeOrderButton.setOnClickListener(v -> {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                // SMS permission not granted. Request permissions
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, SMS_PERMISSION_REQUEST_CODE);
            } else {
                String size = getCheckedRadioText(R.id.size_group);
                String tortilla = getCheckedRadioText(R.id.tortilla_group);
                ArrayList<String> fillings = getCheckedCheckboxText(R.id.fillings_group);
                ArrayList<String> beverages = getCheckedCheckboxText(R.id.beverage_group);

                SmsManager smsManager = SmsManager.getDefault();
                String phoneNumber = "5556";
                String newLine = System.getProperty("line.separator");
                String content = "--- TACO ORDER ---"
                        + newLine
                        + "SIZE: " + size
                        + newLine
                        + "TORTILLA: " + tortilla
                        + newLine
                        + "FILLINGS: " + String.join(", ", fillings)
                        + newLine
                        + "BEVERAGES: " + String.join(", ", beverages);

                smsManager.sendTextMessage(
                        phoneNumber,
                        null,
                        content,
                        null,
                        null
                );
            }
        });
    }
}