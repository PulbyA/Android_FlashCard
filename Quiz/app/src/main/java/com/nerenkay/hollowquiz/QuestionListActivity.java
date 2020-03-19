package com.nerenkay.hollowquiz;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class QuestionListActivity extends AppCompatActivity {

    private ArrayList<Question> questions;
    private QuestionAdapter adapter;
    private Boolean isPlayed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);

        Intent srcIntent = getIntent();
        isPlayed = srcIntent.getBooleanExtra("aIsPlayed", false);

        questions = new ArrayList<>();

        questions.add(new Question("Qui est cette chose ?",
                "Nerenkay",
                "Antonin",
                "L'étrange homme de la GGJ",
                "Moi",
                "Nerenkay",
                R.drawable.real_me
        ));
        questions.add(new Question("De quel jeu est extraite cette image ?",
                "Hollow Knight",
                "Ori And The Blind Forest",
                "Super Metroid",
                "A Robot Named Fight",
                "Hollow Knight",
                R.drawable.hk
        ));

        if(isPlayed){
            Intent intent = new Intent(QuestionListActivity.this, FlashCardActivity.class);
            Collections.shuffle(questions);
            intent.putExtra("aQuestion", questions.get(0));
            intent.putParcelableArrayListExtra("aArrayListQuestion",questions);
            startActivity(intent);
            finish();
        }else{
            adapter = new QuestionAdapter(questions);

            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

    }
}
