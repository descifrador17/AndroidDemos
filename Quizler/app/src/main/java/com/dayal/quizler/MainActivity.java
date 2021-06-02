package com.dayal.quizler;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Member Variables
    private TrueFalse[] Ques_array = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
    };
    TextView mQuestionText;
    TextView mScoreText;
    ProgressBar mProgress_bar;
    int mIndex=0;
    int mQuesID;
    int mScore=0;


    //Constants
    final int Progress_Fill = (int) (Math.ceil(100.0/Ques_array.length));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null)
        {
            mScore=savedInstanceState.getInt("ScoreKey");
            mIndex=savedInstanceState.getInt("IndexKey");
        }
        else
        {
            mIndex=mScore=0;;
        }

        //Connecting IDs to the variables
        mQuestionText = (TextView) findViewById(R.id.question);
        mScoreText = (TextView) findViewById(R.id.Score_text);
        mProgress_bar = (ProgressBar) findViewById(R.id.progressBar);

        mScoreText.setText("Score : " + mScore + "/" + Ques_array.length);
        mQuesID = Ques_array[mIndex].getQuestionID();
        mQuestionText.setText(mQuesID);

        findViewById(R.id.TrueButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checker(true);
                Update();
            }
        });

        findViewById(R.id.FalseButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checker(false);
                Update();
            }
        });
    }
    //updating
    private void Update()
    {
        mIndex = (mIndex + 1) % Ques_array.length;
        if(mIndex == 0)
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("GAME OVER");
            alert.setMessage("Congrats!! You Scored " + mScore + " points!!");
            alert.setCancelable(false);
            alert.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.show();
        }

        mQuesID = Ques_array[mIndex].getQuestionID();
        mQuestionText.setText(mQuesID);
        mScoreText.setText("Score : " + mScore + "/" + Ques_array.length);
        mProgress_bar.incrementProgressBy(Progress_Fill);
    }

    //Function to check true or false
    void Checker(boolean in_ans)
    {
        if(Ques_array[mIndex].isAnswer()==in_ans)
        {
            Toast.makeText(getApplicationContext(),R.string.true_toast,Toast.LENGTH_SHORT).show();
            mScore++;
        }
        else
        {
            Toast.makeText(getApplicationContext(),R.string.false_toast,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle OnSaveInstance)
    {
        super.onSaveInstanceState(OnSaveInstance);

        OnSaveInstance.putInt("ScoreKey",mScore);
        OnSaveInstance.putInt("IndexKey",mIndex);
    }

}
