package com.example.main;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class LandmarkDialog extends AppCompatDialogFragment {
    public Button openMapButton, moreInfoButton;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.landmark_dialog, null);

        builder.setView(view)
                .setTitle("What do you want to do?")
                .setNegativeButton("cancel", (dialog, which) -> {

                });

        openMapButton = view.findViewById(R.id.open_map_button);
        moreInfoButton = view.findViewById(R.id.more_info_button);

        Bundle bundle = getArguments();
        String mapUrl = bundle.getString("mapUrl");
        String infoUrl = bundle.getString("infoUrl");

        openMapButton.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mapUrl));
            startActivity(browserIntent);
        });

        moreInfoButton.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(infoUrl));
            startActivity(browserIntent);
        });

        return builder.create();
    }
}
