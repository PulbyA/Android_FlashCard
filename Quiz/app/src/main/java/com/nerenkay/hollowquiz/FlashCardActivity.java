package com.nerenkay.hollowquiz;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FlashCardActivity extends AppCompatActivity {

    //Init the few Strings used multiple times in code as const
    final String ANSWER_VALIDATION = "Valider la réponse";
    final String NEXT_QUESTION = "Question suivante";
    final String SEE_RESULTS = "Voir les résultats";

    RadioButton selectedRadioButton;
    int countGoodAnswer;
    ArrayList<Question> questions;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card);


        final RadioGroup answerRadioGroup = findViewById(R.id.answerRadioGroup);
        final TextView questionTextView = findViewById(R.id.questionTextView);
        final ImageView questionImageView = findViewById(R.id.questionImageView);
        final TextView resultTextView = findViewById(R.id.resultTextView);
        final TextView answerTextView = findViewById(R.id.answerTextView);
        final Button nextButton = findViewById(R.id.nextButton);
        final TextView indexTextView = findViewById(R.id.indexTextView);

        Intent srcIntent = getIntent();
        final Question question = srcIntent.getParcelableExtra("aQuestion");
        questions = srcIntent.getParcelableArrayListExtra("aArrayListQuestion");
        if(questions == null ){ questions = new ArrayList<>(); questions.add(question);}
        final int questionPos = srcIntent.getIntExtra("aQuestionPos", 0);
        final int userScore = srcIntent.getIntExtra("aUserScore", 0);

        if (question != null) {
            questionTextView.setText(question.getEntitledQuestion());
            questionImageView.setImageResource(question.getImageID());
            nextButton.setText(ANSWER_VALIDATION);
            indexTextView.setText(questionPos + 1 + " / "+ questions.size());

            //RadioButtons dynamically created inside the RadioGroup
            for (int i = 0; i < question.getAnswerChoices().size(); i++) {
                RadioButton rdbtn = new RadioButton(this);
                rdbtn.setText(question.getAnswerChoices().get(i).toString());
                answerRadioGroup.addView(rdbtn, i);
            }

        }

        //OnClick that change with the button, if the answer is validated, the button will change
        // into a next question button, to go to the next question on the list, and in the end of
        //the list, it will change to print the statistics of the quiz

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nextButton.getText().equals(ANSWER_VALIDATION)) {
                    int selectedId = answerRadioGroup.getCheckedRadioButtonId();
                    selectedRadioButton = findViewById(selectedId);
                    try {
                        if (selectedRadioButton.getText().equals(question.getAnswer())) {
                            resultTextView.setText("VICTOIRE");
                            countGoodAnswer = userScore + 1;
                        } else {
                            resultTextView.setText("défaite");
                            countGoodAnswer = userScore;
                        }


                        answerTextView.setText("La réponse était : " + question.getAnswer());
                        for (int i = 0; i < answerRadioGroup.getChildCount(); i++) {
                            answerRadioGroup.getChildAt(i).setEnabled(false);
                        }

                        if (questionPos == questions.size() - 1) {

                            nextButton.setText(SEE_RESULTS);

                        } else {

                            nextButton.setText(NEXT_QUESTION);

                        }
                    } catch (NullPointerException npe) {
                        Log.e("FlashCardActivity", "npe, pad'bol");
                        Toast.makeText(FlashCardActivity.this,
                                "Réponse obligatoire",
                                Toast.LENGTH_SHORT)
                                .show();
                    }
                } else if (nextButton.getText().equals(SEE_RESULTS)) {
                    Intent intent = new Intent(FlashCardActivity.this, StatisticsActivity.class);
                    intent.putExtra("aUserScore", countGoodAnswer);
                    intent.putExtra("aTotalQuestions", questions.size());
                    startActivity(intent);
                    finish();
                    Log.i("FlashCardActivity", SEE_RESULTS);
                } else {

                    int newPos = questionPos + 1;
                    Intent intent = new Intent(FlashCardActivity.this, FlashCardActivity.class);
                    intent.putExtra("aQuestion", questions.get(newPos));
                    intent.putParcelableArrayListExtra("aArrayListQuestion", questions);
                    intent.putExtra("aQuestionPos", newPos);
                    intent.putExtra("aUserScore", countGoodAnswer);
                    startActivity(intent);
                    finish();

                    Log.i("FlashCardActivity", NEXT_QUESTION);
                }
            }
        });

        // This is to view the picture in a bigger screen if you click on it
        questionImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FlashCardActivity.this, ZoomImageActivity.class);
                intent.putExtra("aZoomImageId", question.getImageID());
                startActivity(intent);
            }
        });
    }
}

