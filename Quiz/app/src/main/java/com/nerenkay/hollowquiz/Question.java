package com.nerenkay.hollowquiz;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Collections;

public class Question implements Parcelable {

    private final String entitledQuestion;
    private ArrayList<String> answerChoices = new ArrayList<>();
    private final String answer;
    private final int resourceId;

    public Question(String entitledQuestion,
                    ArrayList<String> answerChoices,
                    String answer,
                    int resourceId
    ){
        this.answerChoices = answerChoices;

        Collections.shuffle(answerChoices);

        this.entitledQuestion = entitledQuestion;
        this.answer = answer;
        this.resourceId = resourceId;


    }

    protected Question(Parcel in) {
        entitledQuestion = in.readString();
        answerChoices = in.createStringArrayList();
        answer = in.readString();
        resourceId = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(entitledQuestion);
        dest.writeStringList(answerChoices);
        dest.writeString(answer);
        dest.writeInt(resourceId);
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

    public int getResourceId(){
        return resourceId;
    }

}
