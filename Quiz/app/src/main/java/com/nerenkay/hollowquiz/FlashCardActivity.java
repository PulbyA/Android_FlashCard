package com.nerenkay.hollowquiz;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class FlashCardActivity extends AppCompatActivity {

    final String ANSWER_VALIDATION = "Valider la réponse";
    final String NEXT_QUESTION = "Question suivante";
    final String SEE_RESULTS = "Voir les résultats";

    RadioButton selectedRadioButton;
    ArrayList<RadioButton> radioButtons = new ArrayList<RadioButton>();

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

        Intent srcIntent = getIntent();
        final Question question = srcIntent.getParcelableExtra("aQuestion");
        final ArrayList<Question> questions = srcIntent.getParcelableArrayListExtra("aArrayListQuestion");
        final int questionPos = srcIntent.getIntExtra("aQuestionPos", 0);

        if (question != null) {
            questionTextView.setText(question.getEntitledQuestion());
            questionImageView.setImageResource(question.getResourceId());
            nextButton.setText(ANSWER_VALIDATION);

            Log.i("FlashCardActivity", answerRadioGroup.getChildCount() + "");

            //RadioButtons Création

            Log.i("FlashCardActivity", question.getAnswerChoices().size() + "ghghgfhghgf");
            for (int i = 0; i < question.getAnswerChoices().size(); i++) {
                RadioButton rdbtn = new RadioButton(this);
                rdbtn.setText(question.getAnswerChoices().get(i).toString());
                answerRadioGroup.addView(rdbtn, i);
            }

            Log.i("FlashCardActivity", answerRadioGroup.getChildCount() + "");

        }


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nextButton.getText().equals(ANSWER_VALIDATION)) {
                    int selectedId = answerRadioGroup.getCheckedRadioButtonId();
                    selectedRadioButton = findViewById(selectedId);
                    try {
                        if (selectedRadioButton.getText().equals(question.getAnswer())) {
                            resultTextView.setText("VICTOIRE");
                        } else {
                            resultTextView.setText("défaite");
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
                    Intent intent = new Intent(FlashCardActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    Log.i("FlashCardActivity", SEE_RESULTS);
                } else {

                    int newPos = questionPos + 1;
                    Intent intent = new Intent(FlashCardActivity.this, FlashCardActivity.class);
                    intent.putExtra("aQuestion", questions.get(newPos));
                    intent.putParcelableArrayListExtra("aArrayListQuestion", questions);
                    intent.putExtra("aQuestionPos", newPos);
                    startActivity(intent);
                    finish();

                    Log.i("FlashCardActivity", NEXT_QUESTION);
                }
            }
        });
    }
}

