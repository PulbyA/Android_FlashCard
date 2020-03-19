package com.nerenkay.hollowquiz;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {

    private final String entitledQuestion;
    private final String answerChoice_1;
    private final String answerChoice_2;
    private final String answerChoice_3;
    private final String answerChoice_4;
    private final String answer;
    private final int resourceId;

    public Question(String entiteldQuestion,
                    String answerChoice_1,
                    String answerChoice_2,
                    String answerChoice_3,
                    String answerChoice_4,
                    String answer,
                    int resourceId
    ){
        this.entitledQuestion = entiteldQuestion;
        this.answerChoice_1 = answerChoice_1;
        this.answerChoice_2 = answerChoice_2;
        this.answerChoice_3 = answerChoice_3;
        this.answerChoice_4 = answerChoice_4;
        this.answer = answer;
        this.resourceId = resourceId;

    }

    protected Question(Parcel in) {
        entitledQuestion = in.readString();
        answerChoice_1 = in.readString();
        answerChoice_2 = in.readString();
        answerChoice_3 = in.readString();
        answerChoice_4 = in.readString();
        answer = in.readString();
        resourceId = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(entitledQuestion);
        dest.writeString(answerChoice_1);
        dest.writeString(answerChoice_2);
        dest.writeString(answerChoice_3);
        dest.writeString(answerChoice_4);
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

    public String getAnswerChoice_1(){
        return answerChoice_1;
    }

    public String getAnswerChoice_2(){
        return answerChoice_2;
    }

    public String getAnswerChoice_3(){
        return answerChoice_3;
    }

    public String getAnswerChoice_4(){
        return answerChoice_4;
    }

    public String getAnswer(){
        return answer;
    }

    public int getResourceId(){
        return resourceId;
    }

}
