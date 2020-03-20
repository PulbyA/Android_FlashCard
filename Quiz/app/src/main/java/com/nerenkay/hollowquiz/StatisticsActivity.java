package com.nerenkay.hollowquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class StatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        Intent srcIntent = getIntent();
        final int userScore = srcIntent.getIntExtra("aUserScore", 0);
        final int totalQuestions = srcIntent.getIntExtra("aTotalQuestions", 0);

        final TextView scoreTextView = findViewById(R.id.scoreTextView);
        final TextView percentageTextView = findViewById(R.id.percentageTextView);

        scoreTextView.setText(userScore + " / "+totalQuestions);
        percentageTextView.setText((userScore * 100.0f) / totalQuestions + " %");

    }
}
