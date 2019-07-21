package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    Button  button0;
    Button  button1;
    Button  button2;
    Button  button3;
    Button playAgainButton;

    int locationOfAnswer;
    int score = 0;
    int totalQuestions = 0;

    TextView problemTextView;
    TextView resultTextView;
    TextView scoreTextView;
    TextView timeTextView;

    ArrayList<Integer> answers = new ArrayList<>();
    ConstraintLayout gameLayout;

    public void startGame(View view){
        startButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        startAgain(timeTextView);
    }

    public void startAgain(View view){
        score = 0;
        totalQuestions = 0;
        timeTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(totalQuestions));
        resultTextView.setText("");

        newQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);

        new CountDownTimer(30100, 1000){
            @Override
            public void onTick(long l) {
                timeTextView.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Completed.");
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void chooseAnswer(View view){

        if(view.getTag().toString().equals(Integer.toString(locationOfAnswer))){
            resultTextView.setText("Correct!");
            score++;
        }else{
            resultTextView.setText("Wrong! :/");
        }
        totalQuestions++;

        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(totalQuestions));

        newQuestion();

    }

    public void newQuestion(){
        Random random = new Random();

        int a = random.nextInt(21);
        int b = random.nextInt(21);

        problemTextView.setText(a +" + " + b);

        locationOfAnswer = random.nextInt(4);

        answers.clear();

        for(int i=0; i < 4; i++){
            if(i == locationOfAnswer){
                answers.add(a + b);
            }else
            {
                int wrongAnswer = random.nextInt(41);

                while(wrongAnswer == a+b){
                    wrongAnswer = random.nextInt(41);
                }

                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        playAgainButton = findViewById(R.id.startAgainButton);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        problemTextView = findViewById(R.id.problemTextView);
        timeTextView = findViewById(R.id.timerTextView);
        gameLayout = findViewById(R.id.gameLayout);

        startButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);
    }
}
