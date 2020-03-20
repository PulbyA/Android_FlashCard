package com.nerenkay.hollowquiz;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Collections;

public class Question implements Parcelable {

    private final String entitledQuestion;
    private ArrayList<String> answerChoices = new ArrayList<>();
    private final String answer;
    private final Integer imageID;
    private String difficulty;

    public Question(String entitledQuestion,
                    ArrayList<String> answerChoices,
                    String answer,
                    Integer imageID,
                    String difficulty
    ){
        this.answerChoices = answerChoices;

        Collections.shuffle(answerChoices);

        this.entitledQuestion = entitledQuestion;
        this.answer = answer;
        this.imageID = imageID;
        this.difficulty = difficulty;
    }

    protected Question(Parcel in) {
        entitledQuestion = in.readString();
        answerChoices = in.createStringArrayList();
        answer = in.readString();
        imageID = in.readInt();
        difficulty = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(entitledQuestion);
        dest.writeStringList(answerChoices);
        dest.writeString(answer);
        dest.writeInt(imageID);
        dest.writeString(difficulty);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public String getEntitledQuestion(){
        return entitledQuestion;
    }

    public ArrayList getAnswerChoices(){
        return answerChoices;
    }

    public String getAnswer(){
        return answer;
    }

    public Integer getImageID(){
        return imageID;
    }

    public String getDifficulty(){return difficulty; }

}
