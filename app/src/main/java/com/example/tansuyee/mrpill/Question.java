package com.example.tansuyee.mrpill;

public abstract class Question {
    String questions[], choices[][], correctAnswer[];
    abstract String getQuestion(int a);
    abstract String getchoice1(int a);
    abstract String getchoice2(int a);
    abstract String getchoice3(int a);
    abstract String getchoice4(int a);
    abstract String getCorrectAnswer(int a);
}
