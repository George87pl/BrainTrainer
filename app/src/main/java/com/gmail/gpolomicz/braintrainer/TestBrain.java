package com.gmail.gpolomicz.braintrainer;


import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class TestBrain extends AppCompatActivity {
    private static final String TAG = "TestBrain";

    TextView scoreTextView;
    TextView timer;
    TextView questionTextView;
    TextView resultQuestion;
    Button answer1;
    Button answer2;
    Button answer3;
    Button answer4;
    Button again;
    int result;
    int score=0;
    int questionNumber=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_brain);

        scoreTextView = findViewById(R.id.scoreTextView);
        timer = findViewById(R.id.timerTextView);
        questionTextView = findViewById(R.id.questionTextView);
        answer1 = findViewById(R.id.answer1Button);
        answer2 = findViewById(R.id.answer2Button);
        answer3 = findViewById(R.id.answer3Button);
        answer4 = findViewById(R.id.answer4Button);
        again = findViewById(R.id.againButton);
        resultQuestion = findViewById(R.id.resultTextView);

        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                timer.setText(String.valueOf(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {

                timer.setText("0s");
                resultQuestion.setText("Koniec gry!");
                answer1.setEnabled(false);
                answer2.setEnabled(false);
                answer3.setEnabled(false);
                answer4.setEnabled(false);
                again.setVisibility(View.VISIBLE);
            }
        }.start();

        updateQuestion();

        View.OnClickListener clickButton = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch(v.getId()) {
                    case R.id.answer1Button:
                        if(Integer.parseInt((String)answer1.getText()) == result) {
                            resultQuestion.setText("DOBRZE!");
                            score++;
                        } else {
                            resultQuestion.setText("ŹLE");
                        }
                        break;

                    case R.id.answer2Button:
                        if(Integer.parseInt((String)answer2.getText()) == result) {
                            resultQuestion.setText("DOBRZE!");
                            score++;
                        } else {
                            resultQuestion.setText("ŹLE");
                        }
                        break;

                    case R.id.answer3Button:
                        if(Integer.parseInt((String)answer3.getText()) == result) {
                            resultQuestion.setText("DOBRZE!");
                            score++;
                        } else {
                            resultQuestion.setText("ŹLE");
                        }
                        break;

                    case R.id.answer4Button:
                        if(Integer.parseInt((String)answer4.getText()) == result) {
                            resultQuestion.setText("DOBRZE!");
                            score++;
                        } else {
                            resultQuestion.setText("ŹLE");

                        }
                        break;

                    case R.id.againButton:
                        Intent intent = new Intent(TestBrain.this, TestBrain.class);
                        startActivity(intent);
                        break;
                }
                questionNumber++;
                updateQuestion();

            }
        };

        answer1.setOnClickListener(clickButton);
        answer2.setOnClickListener(clickButton);
        answer3.setOnClickListener(clickButton);
        answer4.setOnClickListener(clickButton);
        again.setOnClickListener(clickButton);

    }

    public void updateQuestion() {

        Random randomNumber = new Random();
        int num1 = randomNumber.nextInt(9)+1;
        int num2 = randomNumber.nextInt(9)+1;
        result = num1 + num2;

        questionTextView.setText(num1 + " + " + num2);
        scoreTextView.setText(getString(R.string.score, score, questionNumber));

        ArrayList<Integer> numberAnserws = new ArrayList<>();
        numberAnserws.add(result);

        for(int i=0; i<3; i++) {
            num1 = randomNumber.nextInt(15) + 7;
            while (num1 == result) {
                num1 = randomNumber.nextInt(15) + 7;
            }
            numberAnserws.add(num1);
        }

        num1 = randomNumber.nextInt(numberAnserws.size());
        answer1.setText(String.valueOf(numberAnserws.get(num1)));
        numberAnserws.remove(num1);

        num1 = randomNumber.nextInt(numberAnserws.size());
        answer2.setText(String.valueOf(numberAnserws.get(num1)));
        numberAnserws.remove(num1);

        num1 = randomNumber.nextInt(numberAnserws.size());
        answer3.setText(String.valueOf(numberAnserws.get(num1)));
        numberAnserws.remove(num1);

        num1 = randomNumber.nextInt(numberAnserws.size());
        answer4.setText(String.valueOf(numberAnserws.get(num1)));
        numberAnserws.remove(num1);

    }
}
