package com.example.tansuyee.mrpill;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_one, btn_two, btn_three, btn_four;
    TextView question_quiz;

    private Question question;
    private String answer;
    private int questionLength;
    private int questionNumber = 0;
    Toast toast = null;

    private TextView timerTextView;
    private CountDownTimer mCountDownTimer;
    private int counter = 10;
    private int topic;
    private String winnerMessage;

    private ArrayList <Button> buttonList = new ArrayList();

    private void resetTimer() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            counter = 10;

            mCountDownTimer = new CountDownTimer(10500,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timerTextView.setText("Timer:" + String.valueOf(counter));
                    counter--;
                }
                public void onFinish() {
                    if (counter == 0) {
                        timerTextView.setText("Timer: 0");
                        GameOver();
                    }
                }
            }.start();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        btn_one = findViewById(R.id.btn_one);
        btn_one.setOnClickListener(this);
        btn_two = findViewById(R.id.btn_two);
        btn_two.setOnClickListener(this);
        btn_three = findViewById(R.id.btn_three);
        btn_three.setOnClickListener(this);
        btn_four = findViewById(R.id.btn_four);
        btn_four.setOnClickListener(this);

        buttonList.add(btn_one);
        buttonList.add(btn_two);
        buttonList.add(btn_three);
        buttonList.add(btn_four);

        question_quiz = findViewById(R.id.question_quiz);
        questionLength = 4;

        Intent intent = getIntent();
        topic = intent.getIntExtra("topicQuiz", 0);
        switch (topic) {
            case 1:
                question = new CoughColdQuestion();
                winnerMessage = "You have unlocked the 'Dry Eyes' quiz!";
                break;
            case 2:
                question = new OticOphthalmicQuestion();
                winnerMessage = "You have unlocked the 'Chronic Pain' quiz!";
                break;
            case 3:
                question = new PainQuestion();
                winnerMessage = "You have unlocked the 'Stomach Conditions' quiz!";
                break;
            case 4:
                question = new GastrointestinalQuestion();
                winnerMessage = "You have unlocked the 'Skin Conditions' quiz!";
                break;
            case 5:
                question = new SkinConditionsQuestion();
                winnerMessage = "You have completed all categories";
                break;
            default:
                question = new CoughColdQuestion();
                winnerMessage = "You have unlocked the 'Chronic Pain' quiz!";
        }

        NextQuestion(questionNumber++);
        timerTextView = findViewById(R.id.timerTextView);
        mCountDownTimer = new CountDownTimer(10500,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText("Timer: " + String.valueOf(counter));
                counter--;
            }
            public void onFinish() {
                if (counter == 0) {
                    timerTextView.setText("Timer: 0");
                    GameOver();
                }
            }
        }.start();
    }

    public void removeTint(final View v) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                v.getBackground().clearColorFilter();
            }
        }, 200);
    }

    @Override
    public void onClick(final View v) {
        if (toast != null) {
            toast.cancel();
        }
        if(((Button)v).getText() == answer){
            v.getBackground().setColorFilter(getResources().getColor(R.color.buttonCorrect), PorterDuff.Mode.MULTIPLY);
            toast = Toast.makeText(this, "You Are Correct", Toast.LENGTH_SHORT);
            toast.show();
            removeTint(v);
            if (questionNumber < questionLength) {
                NextQuestion(questionNumber++);
                resetTimer();
            } else if (questionNumber == questionLength) {
                if (mCountDownTimer != null) {
                    mCountDownTimer.cancel();
                }
                Won();
            }
        } else {
            v.getBackground().setColorFilter(getResources().getColor(R.color.buttonWrong), PorterDuff.Mode.MULTIPLY);
            removeTint(v);
            if (mCountDownTimer != null) {
                mCountDownTimer.cancel();
            }

            for (final Button button : buttonList){
                if(button.getText().equals(answer)) {
                    button.getBackground().setColorFilter(getResources().getColor(R.color.buttonCorrect), PorterDuff.Mode.MULTIPLY);
                    removeTint(button);
                    break;
                }
            }
            GameOver();
        }
    }

    private void Won() {
        if (toast != null) {
            toast.cancel();
        }
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder
                .setMessage("Congratulations! You have successfully completed this level! " + winnerMessage)
                .setCancelable(false)
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(QuizActivity.this, ProfileActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("fragment", 2);
                        intent.putExtra("cleared", Integer.toString(topic));
                        startActivity(intent);
                        finish();
                    }
                });
        alertDialogBuilder.show();
    }

    private void GameOver(){
        if (toast != null) {
            toast.cancel();
        }
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder
                .setMessage(getResources().getString(R.string.game_over))
                .setCancelable(false)
                .setPositiveButton("New Game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(intent);
//                        finish();
                        recreate();
                    }
                })
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                });

        alertDialogBuilder.show();
    }

    private void NextQuestion(int num){
        question_quiz.setText(question.getQuestion(num));
        btn_one.setText(question.getchoice1(num));

        btn_two.setText(question.getchoice2(num));

        btn_three.setText(question.getchoice3(num));

        btn_four.setText(question.getchoice4(num));

        answer = question.getCorrectAnswer(num);
    }
}
