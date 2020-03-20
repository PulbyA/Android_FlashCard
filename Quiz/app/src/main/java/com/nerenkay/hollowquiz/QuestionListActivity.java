package com.nerenkay.hollowquiz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class QuestionListActivity extends AppCompatActivity {

    private ArrayList<Question> questions;
    private ArrayList<Question> questionToSend = new ArrayList<>();
    private QuestionAdapter adapter;
    private Boolean isPlayed;
    private String difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);

        Intent srcIntent = getIntent();
        isPlayed = srcIntent.getBooleanExtra("aIsPlayed", false);
        difficulty = srcIntent.getStringExtra("aDifficulty");

        questions = new ArrayList<>();
        ArrayList<String> answers_1 = new ArrayList<String>();
        answers_1.add("La Cité des Larmes");
        answers_1.add("Dirtmouth");
        answers_1.add("Mont Cristal");
        answers_1.add("La Ruche");
        questions.add(new Question("Quelle est la capitale d’Hallownest ?",
                answers_1,
                "La Cité des Larmes",
                R.drawable.map_hollow_knight,
                "Moyen"
        ));


        ArrayList<String> answers_2 = new ArrayList<String>();
        answers_2.add("La santé");
        answers_2.add("Le nombre d’ennemis tués");
        answers_2.add("L’invisibilité momentanée");
        answers_2.add("L’activation du bouclier");
        questions.add(new Question("Que représente le masque blanc ?",
                answers_2,
                "La santé",
                R.drawable.masque_de_sante,
                "Moyen"
        ));


        ArrayList<String> answers_3 = new ArrayList<String>();
        answers_3.add("À reconstituer sa santé");
        answers_3.add("À passer la nuit");
        answers_3.add("À récupérer de l’endurance");
        answers_3.add("Uniquement à de la décoration");
        questions.add(new Question("À quoi servent les bancs ?",
                answers_3,
                "À reconstituer sa santé",
                R.drawable.banc_commun,
                "Facile"
        ));

        ArrayList<String> answers_4 = new ArrayList<String>();
        answers_4.add("Un chevalier");
        answers_4.add("Un démon");
        answers_4.add("La mort");
        answers_4.add("Un taureau");
        questions.add(new Question("Que représente le personnage principal du jeu ?",
                answers_4,
                "Un chevalier",
                R.drawable.hollow_knight,
                "Facile"
        ));

        ArrayList<String> answers_6 = new ArrayList<String>();
        answers_6.add("Un boss du jeu");
        answers_6.add("Un allié qui suit Hollow Knight partout");
        answers_6.add("La version féminine de Hollow Knight");
        questions.add(new Question("Qui est Hornet ?",
                answers_6,
                "Un boss du jeu",
                R.drawable.hornet_boss,
        "Moyen"
        ));

        ArrayList<String> answers_7 = new ArrayList<String>();
        answers_7.add("Hollow Knight");
        answers_7.add("Le Maître des Âmes");
        answers_7.add("Le Traitre");
        answers_7.add("Irsk");
        questions.add(new Question("Comment s’appelle ce personnage ?",
                answers_7,
                "Hollow Knight",
                R.drawable.hollow_knight_boss,
                "Moyen"
        ));

        ArrayList<String> answers_8 = new ArrayList<String>();
        answers_8.add("Un laissez-passer du tramway");
        answers_8.add("Une clé");
        answers_8.add("Un fragment d’écaille");
        answers_8.add("Le blason de la cité");
        questions.add(new Question("Quel est cet objet ?",
                answers_8,
                "Un laissez-passer du tramway",
                R.drawable.laissez_passer_du_tramway,
                "Difficile"
        ));

        ArrayList<String> answers_9 = new ArrayList<String>();
        answers_9.add("Clé Élégante");
        answers_9.add("Clé de l’Amour");
        answers_9.add("Clé du Roi");
        answers_9.add("Clé des Âmes");
        questions.add(new Question("Quel est le nom de cette clé ?",
                answers_9,
                "Clé Élégante",
                R.drawable.cle_elegante,
                "Difficile"
        ));

        ArrayList<String> answers_11 = new ArrayList<String>();
        answers_11.add("Blundering Oberlisk");
        answers_11.add("Goofy Insekt");
        answers_11.add("Centiped Soldier");
        answers_11.add("Steel Shell Warrior");
        questions.add(new Question("Comment aurait dû s’appeler ce personnage des contenus abandonnés ?",
                answers_11,
                "Blundering Oberlisk",
                R.drawable.blundering_oberlisk,
                "Difficile"
        ));

        ArrayList<String> answers_12 = new ArrayList<String>();
        answers_12.add("Un chevalier blanc");
        answers_12.add("La première version de Hornet");
        answers_12.add("Un guide pour le joueur");
        answers_12.add("Un narrateur");
        questions.add(new Question("Qu’aurait dû être ce personnage des contenus abandonnés ?",
                answers_12,
                "Un chevalier blanc",
                R.drawable.chevalier_blanc,
                "Difficile"
        ));


        ArrayList<String> answers_15 = new ArrayList<String>();
        answers_15.add("À faire office de grappin");
        answers_15.add("À crocheter les coffres");
        answers_15.add("À attaquer les ennemis au corps à corps");
        questions.add(new Question("À quoi sert la pince de mante religieuse ?",
                answers_15,
                "À faire office de grappin",
                R.drawable.pince_de_mante_religieuse,
                "Moyen"
        ));

        if(difficulty != null && !difficulty.isEmpty()){
            for(Question question : questions){
                if(question.getDifficulty().equals(difficulty)){
                    questionToSend.add(question);
                }
            }
            Log.i("QuestionListActivity", difficulty);
        }
        else {
            questionToSend = questions;
        }
        if(isPlayed){
            Intent intent = new Intent(QuestionListActivity.this, FlashCardActivity.class);
            Collections.shuffle(questionToSend);
            intent.putExtra("aQuestion", questionToSend.get(0));
            intent.putParcelableArrayListExtra("aArrayListQuestion",questionToSend);
            startActivity(intent);
            finish();
        }else{
            adapter = new QuestionAdapter(questionToSend);

            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

    }
}
