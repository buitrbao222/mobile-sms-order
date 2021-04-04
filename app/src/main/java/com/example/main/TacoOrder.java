package com.example.main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;

public class TacoOrder extends BaseActivity {
    private static final int SMS_PERMISSION_REQUEST_CODE = 1;

    public void centerToast(String content) {
        Toast toast = Toast.makeText(getApplicationContext(), content, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == SMS_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                this.placeOrder();
            } else {
                centerToast("Need SMS permission to place an order!");
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void placeOrder() {
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

    protected String getCheckedRadioText(@IdRes int radioGroupId) {
        RadioGroup radioGroup = (RadioGroup) findViewById(radioGroupId);
        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton checkedRadioButton = (RadioButton) findViewById(checkedRadioButtonId);
        return checkedRadioButton.getText().toString();
    }

    protected ArrayList<String> getCheckedCheckboxText(@IdRes int linearLayoutId) {
        LinearLayout linearLayout = findViewById(linearLayoutId);

        ArrayList<String> texts = new ArrayList<String>();

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
        setContentView(R.layout.taco_order);

        Button placeOrderButton = findViewById(R.id.place_order_button);

        placeOrderButton.setOnClickListener(v -> {
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.SEND_SMS) !=
                    PackageManager.PERMISSION_GRANTED) {
                // Permission not yet granted. Request permissions
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        SMS_PERMISSION_REQUEST_CODE);
            } else {
                this.placeOrder();
            }
        });
    }
}