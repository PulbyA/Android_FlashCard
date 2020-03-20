package com.nerenkay.hollowquiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String selectedDifficulty;
    int checkedDifficulty = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button playButton = findViewById(R.id.playButton);
        final Button questionListButton = findViewById(R.id.questionListButton);
        final Button aboutButton = findViewById(R.id.aboutButton);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialogDifficulty();
            }
        });

        questionListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(QuestionListActivity.class);
            }
        });

        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(AboutActivity.class);
            }
        });
    }

    //This method will create a Dialog to choose your quiz difficulty with radioButtons, and send
    //this difficulty to the questionList

    public void createDialogDifficulty(){

        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Choisissez une difficulté");

        // add a radio button list
        final String[] difficulties = {"Facile", "Moyen", "Difficile"};
        builder.setSingleChoiceItems(difficulties, checkedDifficulty,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                checkedDifficulty = which;
                Log.i("MainActivity", difficulties[which]);
            }
        });

        // add OK and Cancel buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedDifficulty = difficulties[checkedDifficulty];
                Intent playIntent = new Intent(MainActivity.this, QuestionListActivity.class);
                playIntent.putExtra("aDifficulty", selectedDifficulty);
                playIntent.putExtra("aIsPlayed", true);
                startActivity(playIntent);
            }
        });
        builder.setNegativeButton("Cancel", null);

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // This is just a method to rapidly change to an other view if you don't have any extra to put
    // in the intent

    public void changeActivity(Class activity){
        Intent intent = new Intent(MainActivity.this, activity);
        startActivity(intent);
    }
}
