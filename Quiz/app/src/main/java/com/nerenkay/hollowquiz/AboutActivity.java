package com.nerenkay.hollowquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        final TextView realNameTextView = findViewById(R.id.realNameTextView);
        final TextView pseudoTextView = findViewById(R.id.pseudoTextView);
        final TextView versionTextView = findViewById(R.id.versionTextView);

        realNameTextView.setText("Antonin Pulby");
        pseudoTextView.setText("Nerenkay");
        versionTextView.setText(BuildConfig.VERSION_NAME);
    }
}
