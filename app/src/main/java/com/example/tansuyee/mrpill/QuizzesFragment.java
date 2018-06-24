package com.example.tansuyee.mrpill;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuizzesFragment extends Fragment implements View.OnClickListener {

    public QuizzesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_quizzes, container, false);
        v.findViewById(R.id.btn_coughCold).setOnClickListener(this);
        v.findViewById(R.id.btn_oticOphthalmic).setOnClickListener(this);
        v.findViewById(R.id.btn_pain).setOnClickListener(this);
        v.findViewById(R.id.btn_gastrointestinal).setOnClickListener(this);
        v.findViewById(R.id.btn_skinConditions).setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent (getActivity(), QuizActivity.class);
        switch (view.getId()) {
            case R.id.btn_coughCold:
               intent.putExtra("topic", 1);
               break;
            case R.id.btn_oticOphthalmic:
                intent.putExtra("topic", 2);
                break;
            case R.id.btn_pain:
                intent.putExtra("topic", 3);
                break;
            case R.id.btn_gastrointestinal:
                intent.putExtra("topic", 4);
                break;
            case R.id.btn_skinConditions:
                intent.putExtra("topic", 5);
                break;
        }
        startActivity(intent);
    }
}
