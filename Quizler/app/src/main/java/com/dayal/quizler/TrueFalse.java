package com.dayal.quizler;

class TrueFalse {
    private int QuestionID;
    private boolean Answer;

    TrueFalse(int Ques,boolean ans)
    {
        QuestionID=Ques;
        Answer=ans;
    }

    public int getQuestionID() {
        return QuestionID;
    }

    public void setQuestionID(int questionID) {
        QuestionID = questionID;
    }

    public boolean isAnswer() {
        return Answer;
    }

    public void setAnswer(boolean answer) {
        Answer = answer;
    }
}
