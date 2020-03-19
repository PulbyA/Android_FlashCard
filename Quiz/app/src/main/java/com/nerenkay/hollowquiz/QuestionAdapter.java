package com.nerenkay.hollowquiz;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> implements View.OnClickListener {

    public ArrayList<Question> questions;

    public QuestionAdapter (ArrayList<Question> currencies){
        this.questions = currencies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_question, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Question question = questions.get(position);
        holder.resource.setImageResource(question.getResourceId());
        holder.answerChoice_1.setText(question.getAnswerChoice_1());
        holder.answerChoice_2.setText(question.getAnswerChoice_2());
        holder.answerChoice_3.setText(question.getAnswerChoice_3());
        holder.answerChoice_4.setText(question.getAnswerChoice_4());
        holder.entitledQuestion.setText(question.getEntitledQuestion());

        holder.itemView.setTag(question);
        holder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    @Override
    public void onClick(View v) {
        Log.i("QuestionAdapter", "onclick: CLASS");
        switch (v.getId()) {
            case R.id.rootItem:
                Context context = v.getContext();
                Question q = (Question) v.getTag();
                Intent intent = new Intent(context, FlashCardActivity.class);
                intent.putExtra("aQuestion", q);
                intent.putParcelableArrayListExtra("aArrayListQuestion", questions);
                context.startActivity(intent);
                break;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView resource;
        final TextView answerChoice_1;
        final TextView answerChoice_2;
        final TextView answerChoice_3;
        final TextView answerChoice_4;
        final TextView entitledQuestion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            resource = itemView.findViewById(R.id.resourceImageView);
            answerChoice_1 = itemView.findViewById(R.id.choiceAnswerTextView1);
            answerChoice_2 = itemView.findViewById(R.id.choiceAnswerTextView2);
            answerChoice_3 = itemView.findViewById(R.id.choiceAnswerTextView3);
            answerChoice_4 = itemView.findViewById(R.id.choiceAnswerTextView4);
            entitledQuestion = itemView.findViewById(R.id.entitledQuestionTextView);
        }
    }
}
