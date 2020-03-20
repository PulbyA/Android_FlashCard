package com.nerenkay.hollowquiz;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
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
        ArrayList<String> answers_1 = new ArrayList<String>();
        ArrayList<String> answers_2 = new ArrayList<String>();

        answers_1.add("Nerenkay");
        answers_1.add("Antonin");
        answers_1.add("L'étrange homme de la GGJ");

        questions.add(new Question("Qui est cette chose ?",
                answers_1,
                "Nerenkay",
                R.drawable.real_me
        ));

        answers_2.add("Hollow Knight");
        answers_2.add("Ori And The Blind Forest");
        answers_2.add("Super Metroid");
        answers_2.add("A Robot Named Fight");
        questions.add(new Question("De quel jeu est extraite cette image ?",
                answers_2,
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
