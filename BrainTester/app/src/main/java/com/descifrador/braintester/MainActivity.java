package com.descifrador.braintester;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.gridlayout.widget.GridLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton,option1,option2,option3,option4,playAgain;
    TextView timerTextView,scoreTextView,questionTextView,correct_wrong;
    CountDownTimer countDownTimer;
    ConstraintLayout gameLayout;
    int locationcorrectans;
    List<Integer> list = new ArrayList<>();
    int totalcorrect,totalattempts;


    public void playGameAgain(View view){
        totalattempts = 0;
        totalcorrect = 0;
        list.clear();

        correct_wrong.setVisibility(View.INVISIBLE);

        questionTextView.setVisibility(View.VISIBLE);
        option1.setVisibility(View.VISIBLE);
        option2.setVisibility(View.VISIBLE);
        option3.setVisibility(View.VISIBLE);
        option4.setVisibility(View.VISIBLE);

        scoreTextView.setText("0/0");

        timerTextView.setText("30s");
        countDownTimer.start();

        generateques();
    }

    public void chooseAnswer(View view){
        //checking the location of correct answer
        correct_wrong.setVisibility(View.VISIBLE);
        if(view.getTag().toString().equals(Integer.toString(locationcorrectans))){
            totalcorrect++;
            correct_wrong.setTextColor(Color.GREEN);
            correct_wrong.setText("Correct!");

        }
        else{
            correct_wrong.setTextColor(Color.RED);
            correct_wrong.setText("Wrong!");
        }
        totalattempts++;
        scoreTextView.setText(Integer.toString(totalcorrect)+"/"+Integer.toString(totalattempts));
        list.clear();
        generateques();
    }

    public void startTimer(){
        countDownTimer = new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(Integer.toString((int)millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                correct_wrong.setText("You scored "+Integer.toString(totalcorrect)+"/"+Integer.toString(totalattempts));
                questionTextView.setVisibility(View.INVISIBLE);
                option1.setVisibility(View.INVISIBLE);
                option2.setVisibility(View.INVISIBLE);
                option3.setVisibility(View.INVISIBLE);
                option4.setVisibility(View.INVISIBLE);
                playAgain.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void generateques(){
        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);
        int answer = a+b;
        questionTextView.setText(Integer.toString(a)+"+"+Integer.toString(b));
        locationcorrectans = random.nextInt(4);
        for(int i=0;i<4;i++){
            if(i==locationcorrectans)
                list.add(answer);
            else {
                int incorrectans = random.nextInt(41);
                while (incorrectans == answer)
                    incorrectans = random.nextInt(41);
                list.add(incorrectans);
            }
        }
        option1.setText(Integer.toString(list.get(0)));
        option2.setText(Integer.toString(list.get(1)));
        option3.setText(Integer.toString(list.get(2)));
        option4.setText(Integer.toString(list.get(3)));

    }

    public void LetsPlay(View view){
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        startTimer();
        generateques();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.gobutton);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        playAgain = findViewById(R.id.playAgainButton);
        timerTextView = findViewById(R.id.TimerTextView);
        scoreTextView = findViewById(R.id.ScoreTextView);
        questionTextView = findViewById(R.id.QuestionTextView);
        correct_wrong = findViewById(R.id.correctWrong);
        gameLayout = findViewById(R.id.gameLayout);

        correct_wrong.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

    }
}
