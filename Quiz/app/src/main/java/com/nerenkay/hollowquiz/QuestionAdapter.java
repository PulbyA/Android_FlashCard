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

    public QuestionAdapter (ArrayList<Question> questions){
        this.questions = questions;
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
        if(question.getImageID() != null){
            holder.imageID.setImageResource(question.getImageID());
        }
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
                //intent.putParcelableArrayListExtra("aArrayListQuestion", questions);
                context.startActivity(intent);
                break;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView imageID;
        final TextView entitledQuestion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageID = itemView.findViewById(R.id.resourceImageView);
            entitledQuestion = itemView.findViewById(R.id.entitledQuestionTextView);
        }
    }
}
