package com.nerenkay.hollowquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class FlashCardActivity extends AppCompatActivity {

    RadioButton selectedRadioButton;
    ArrayList<Question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card);

        final TextView questionTextView = findViewById(R.id.questionTextView);
        final ImageView questionImageView = findViewById(R.id.questionImageView);
        final RadioGroup answerRadioGroup = findViewById(R.id.answerRadioGroup);
        final RadioButton answerRadioButton1 = findViewById(R.id.answerRadioButton1);
        final RadioButton answerRadioButton2 = findViewById(R.id.answerRadioButton2);
        final RadioButton answerRadioButton3 = findViewById(R.id.answerRadioButton3);
        final RadioButton answerRadioButton4 = findViewById(R.id.answerRadioButton4);
        final TextView resultTextView = findViewById(R.id.resultTextView);
        final TextView answerTextView = findViewById(R.id.answerTextView);
        final Button nextButton = findViewById(R.id.nextButton);
        final Button validateButton = findViewById(R.id.validateButton);
        final Button resultButton = findViewById(R.id.resultButton);

        Intent srcIntent = getIntent();
        final Question question = srcIntent.getParcelableExtra("aQuestion");
        questions = srcIntent.getParcelableArrayListExtra("aArrayListQuestion");
        final int questionPos = srcIntent.getIntExtra("aQuestionPos", 0);

        if(question != null){
            questionTextView.setText(question.getEntitledQuestion());
            questionImageView.setImageResource(question.getResourceId());
            answerRadioButton1.setText(question.getAnswerChoice_1());
            answerRadioButton2.setText(question.getAnswerChoice_2());
            answerRadioButton3.setText(question.getAnswerChoice_3());
            answerRadioButton4.setText(question.getAnswerChoice_4());
            validateButton.setText("Valider la réponse");
            nextButton.setText("Question suivante");
            resultButton.setText("Voir les résultats");
        }

        nextButton.setVisibility(View.INVISIBLE);
        nextButton.setClickable(false);

        validateButton.setVisibility(View.VISIBLE);
        validateButton.setClickable(true);

        resultButton.setVisibility(View.INVISIBLE);
        resultButton.setClickable(false);

        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = answerRadioGroup.getCheckedRadioButtonId();
                selectedRadioButton=findViewById(selectedId);
                if(selectedRadioButton.getText().equals(question.getAnswer())){
                    resultTextView.setText("VICTOIRE");
                }
                else {
                    resultTextView.setText("défaite");
                }
                answerTextView.setText("La réponse était : " + question.getAnswer());
                for (int i = 0; i < answerRadioGroup.getChildCount(); i++) {
                    answerRadioGroup.getChildAt(i).setEnabled(false);
                }

                if(questionPos == questions.size() - 1){

                    nextButton.setVisibility(View.INVISIBLE);
                    nextButton.setClickable(false);

                    validateButton.setVisibility(View.INVISIBLE);
                    validateButton.setClickable(false);

                    resultButton.setVisibility(View.VISIBLE);
                    resultButton.setClickable(true);

                } else {

                    nextButton.setVisibility(View.VISIBLE);
                    nextButton.setClickable(true);

                    validateButton.setClickable(false);
                    validateButton.setVisibility(View.INVISIBLE);

                    resultButton.setVisibility(View.INVISIBLE);
                    resultButton.setClickable(false);
                }

            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newPos = questionPos + 1;
                Intent intent = new Intent(FlashCardActivity.this, FlashCardActivity.class);
                intent.putExtra("aQuestion", questions.get(newPos));
                intent.putParcelableArrayListExtra("aArrayListQuestion", questions);
                intent.putExtra("aQuestionPos", newPos);
                startActivity(intent);
                finish();
            }
        });

        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FlashCardActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
