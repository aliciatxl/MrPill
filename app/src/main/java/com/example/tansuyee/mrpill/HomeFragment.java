package com.example.tansuyee.mrpill;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        v.findViewById(R.id.btn_coughCold).setOnClickListener(this);
        v.findViewById(R.id.btn_oticOphthalmic).setOnClickListener(this);
        v.findViewById(R.id.btn_pain).setOnClickListener(this);
        v.findViewById(R.id.btn_gastrointestinal).setOnClickListener(this);
        v.findViewById(R.id.btn_skinConditions).setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), InfoActivity.class);
        intent.putExtra("topicHome", Integer.parseInt(view.getTag().toString()));
        startActivity(intent);
    }
}
