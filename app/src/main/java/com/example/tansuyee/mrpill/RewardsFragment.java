package com.example.tansuyee.mrpill;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class RewardsFragment extends Fragment implements View.OnClickListener{

    ImageView bean, greenDot;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor preferencesEditor;
    public static final String PAIN_UNLOCKED = "painUnlocked";
    public static final String COMPLETED = "completedAll";

    public RewardsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_rewards, container, false);
        mPreferences = getActivity().getSharedPreferences("com.example.tansuyee.mrpill", MODE_PRIVATE);
        preferencesEditor = mPreferences.edit();
        bean = v.findViewById(R.id.bean);
        greenDot = v.findViewById(R.id.greenDot);
        bean.setOnClickListener(this);
        greenDot.setOnClickListener(this);

        if (Objects.equals(mPreferences.getString(PAIN_UNLOCKED, null), "pain_unlocked")) {
            if (!Objects.equals(mPreferences.getString("claimed", null), bean.getTag().toString())) {
                bean.setVisibility(View.VISIBLE);
            }
        }
        if (Objects.equals(mPreferences.getString(COMPLETED, null), "completed_all")) {
            if (!Objects.equals(mPreferences.getString("claimed", null), greenDot.getTag().toString())) {
                greenDot.setVisibility(View.VISIBLE);
            }
        }
        return v;
    }

    @Override
    public void onClick(final View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder
                .setMessage("Congratulations! You have successfully claimed the " + view.getTag() + " vouchers!")
                .setCancelable(false)
                .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        view.setVisibility(View.GONE);
                        preferencesEditor.putString("claimed", view.getTag().toString()).apply();
                    }
                });
        alertDialogBuilder.show();
    }
}
