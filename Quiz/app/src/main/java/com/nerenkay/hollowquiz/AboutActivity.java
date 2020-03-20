package com.nerenkay.hollowquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    final String REALNAME = "Antonin Pulby";
    final String PSEUDO = "Nerenkay";
    final String VERSION = BuildConfig.VERSION_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        final TextView realNameTextView = findViewById(R.id.realNameTextView);
        final TextView pseudoTextView = findViewById(R.id.pseudoTextView);
        final TextView versionTextView = findViewById(R.id.versionTextView);

        realNameTextView.setText(REALNAME);
        pseudoTextView.setText(PSEUDO);
        versionTextView.setText(VERSION);
    }
}
