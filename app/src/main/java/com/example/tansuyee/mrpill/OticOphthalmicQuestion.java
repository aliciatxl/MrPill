package com.example.tansuyee.mrpill;

public class OticOphthalmicQuestion extends Question {
    public String questions[] = {
            "3Which is a common feature of common cold?",
            "3Which is the usual distinguishing feature of allergic rhinitis from common cold?",
            "3What should you NOT do during a fever?",
            "3What should you do to avoid allergens?"
    };

    public String choices[][] = {
            {"Abrupt onset", "High fever", "Severe headache", "Sore throat"},
            {"Nasal congestion", "Nose itch", "Sneezing", "Sore throat"},
            {"Drink plenty of water", "Rubbing alcohol on skin", "Tepid sponging with warm water", "Well-ventilated room"},
            {"Air Conditioning","Try to avoid animals","Use of air filters","All of the above"}
    };

    public String correctAnswer[] = {
            "Sore throat",
            "Nose itch",
            "Rubbing alcohol on skin",
            "All of the above"
    };

    public String getQuestion(int a){
        String question = questions[a];
        return question;
    }

    public String getchoice1(int a){
        String choice = choices[a][0];
        return choice;
    }

    public String getchoice2(int a){
        String choice = choices[a][1];
        return choice;
    }

    public String getchoice3(int a){
        String choice = choices[a][2];
        return choice;
    }

    public String getchoice4(int a){
        String choice = choices[a][3];
        return choice;
    }

    public String getCorrectAnswer(int a){
        String answer = correctAnswer[a];
        return answer;
    }
}
