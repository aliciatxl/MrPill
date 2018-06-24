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
public class QuizzesFragment extends Fragment {

    Button btn_coughCold;

    /* public void coughColdButton(View view) {
        Intent intent = new Intent (getActivity(), CoughColdQuiz.class);
        startActivity(intent);
    }
*/
    /*public void oticOphthalmicButton(View view) {
        Intent intent = new Intent (this, OticOphthalmicQuiz.class);
        startActivity(intent);
    }

    public void painButton(View view) {
        Intent intent = new Intent (this, PainQuiz.class);
        startActivity(intent);
    }

    public void gastrointestinalButton(View view) {
        Intent intent = new Intent (this, GastrointestinalQuiz.class);
        startActivity(intent);
    }

    public void skinConditionsButton(View view) {
        Intent intent = new Intent (this, SkinConditionsQuiz.class);
        startActivity(intent);
    }
*/
    public QuizzesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_quizzes, container, false);
        btn_coughCold = v.findViewById(R.id.btn_coughCold);
        btn_coughCold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getActivity(), CoughColdQuiz.class);
                startActivity(intent);
            }
        });
        return v;
    }

}
