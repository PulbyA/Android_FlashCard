package com.nerenkay.hollowquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView welcomeTextView = findViewById(R.id.welcomeTextView) ;
        final Button playButton = findViewById(R.id.playButton);
        final Button questionListButton = findViewById(R.id.questionListButton);
        final Button aboutButton = findViewById(R.id.aboutButton);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MainActivity", "playButton");
                Intent playIntent = new Intent(MainActivity.this, QuestionListActivity.class);
                playIntent.putExtra("aIsPlayed", true);
                startActivity(playIntent);
            }
        });

        questionListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MainActivity", "questionListButton");
                Intent intent = new Intent(MainActivity.this, QuestionListActivity.class);
                startActivity(intent);
            }
        });

        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MainActivity", "aboutButton");
            }
        });
    }
}
