package com.example.tansuyee.mrpill;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_one, btn_two, btn_three, btn_four;
    TextView question_quiz;

    private Question question;

    private String answer;
    private int questionLength;
    private int questionNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        btn_one = (Button)findViewById(R.id.btn_one);
        btn_one.setOnClickListener(this);
        btn_two = (Button)findViewById(R.id.btn_two);
        btn_two.setOnClickListener(this);
        btn_three = (Button)findViewById(R.id.btn_three);
        btn_three.setOnClickListener(this);
        btn_four = (Button)findViewById(R.id.btn_four);
        btn_four.setOnClickListener(this);

        question_quiz = (TextView)findViewById(R.id.question_quiz);
        questionLength = 4;

        Intent intent = getIntent();
        int topic = intent.getIntExtra("topicQuiz", 0);
        switch (topic) {
            case 1:
                question = new CoughColdQuestion();
                break;
            case 2:
                question = new OticOphthalmicQuestion();
                break;
            case 3:
                question = new PainQuestion();
                break;
            case 4:
                question = new GastrointestinalQuestion();
                break;
            case 5:
                question = new SkinConditionsQuestion();
                break;
            default:
                question = new CoughColdQuestion();

        }

        NextQuestion(questionNumber++);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_one:
                if(btn_one.getText() == answer){
                    Toast.makeText(this, "You Are Correct", Toast.LENGTH_SHORT).show();
                    if (questionNumber < questionLength) {
                        NextQuestion(questionNumber++);
                    }else if (questionNumber == questionLength){
                        Won();
                    }
                }else{
                    GameOver();
                }

                break;

            case R.id.btn_two:
                if(btn_two.getText() == answer){
                    Toast.makeText(this, "You Are Correct", Toast.LENGTH_SHORT).show();
                    if (questionNumber < questionLength) {
                        NextQuestion(questionNumber++);
                    }else if (questionNumber == questionLength){
                        Won();
                    }
                }else{
                    GameOver();
                }

                break;

            case R.id.btn_three:
                if(btn_three.getText() == answer){
                    Toast.makeText(this, "You Are Correct", Toast.LENGTH_SHORT).show();
                    if (questionNumber < questionLength) {
                        NextQuestion(questionNumber++);
                    }else if (questionNumber == questionLength){
                        Won();
                    }
                }else{
                    GameOver();
                }

                break;

            case R.id.btn_four:
                if(btn_four.getText() == answer){
                    Toast.makeText(this, "You Are Correct", Toast.LENGTH_SHORT).show();
                    if (questionNumber < questionLength) {
                        NextQuestion(questionNumber++);
                    }else if (questionNumber == questionLength){
                        Won();
                    }
                }else{
                    GameOver();
                }

                break;
        }
    }

    private void Won() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder
                .setMessage("Congratulations! You have successfully completed this level! You have unlocked 'Otic and Ophthalmic' quiz! ")
                .setCancelable(false)
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                });
        alertDialogBuilder.show();
    }

    private void GameOver(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder
                .setMessage("Sorry! Try harder next time!")
                .setCancelable(false)
                .setPositiveButton("New Game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
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
