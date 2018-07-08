package com.example.tansuyee.mrpill;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuizzesFragment extends Fragment implements View.OnClickListener {

    private SharedPreferences mPreferences;
    public static final String EYE_UNLOCKED = "eyeUnlocked";
    public static final String PAIN_UNLOCKED = "painUnlocked";
    public static final String STOMACH_UNLOCKED = "stomachUnlocked";
    public static final String SKIN_UNLOCKED = "skinUnlocked";

    Button btn_oticOphthalmic, btn_coughCold, btn_pain, btn_gastrointestinal, btn_skinConditions;

    public QuizzesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_quizzes, container, false);
        mPreferences = getActivity().getSharedPreferences("com.example.tansuyee.mrpill", MODE_PRIVATE);
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();

        btn_coughCold = v.findViewById(R.id.btn_coughCold);
        btn_coughCold.setOnClickListener(this);
        btn_oticOphthalmic = v.findViewById(R.id.btn_oticOphthalmic);
        btn_oticOphthalmic.setOnClickListener(this);
        btn_pain = v.findViewById(R.id.btn_pain);
        btn_pain.setOnClickListener(this);
        btn_gastrointestinal = v.findViewById(R.id.btn_gastrointestinal);
        btn_gastrointestinal.setOnClickListener(this);
        btn_skinConditions = v.findViewById(R.id.btn_skinConditions);
        btn_skinConditions.setOnClickListener(this);

        // Restore preferences
        if (Objects.equals(mPreferences.getString(EYE_UNLOCKED, null), "eye_unlocked")) {
            btn_oticOphthalmic.setEnabled(true);
        }
        if (Objects.equals(mPreferences.getString(PAIN_UNLOCKED, null), "pain_unlocked")) {
            btn_pain.setEnabled(true);
        }
        if (Objects.equals(mPreferences.getString(STOMACH_UNLOCKED, null), "stomach_unlocked")) {
            btn_gastrointestinal.setEnabled(true);
        }
        if (Objects.equals(mPreferences.getString(SKIN_UNLOCKED, null), "skin_unlocked")) {
            btn_skinConditions.setEnabled(true);
        }

        return v;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent (getActivity(), QuizActivity.class);
        intent.putExtra("topicQuiz", Integer.parseInt(view.getTag().toString()));
        startActivity(intent);
    }
}
