package com.example.tansuyee.mrpill;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;

public class SettingsFragment extends Fragment implements View.OnClickListener {

    private FirebaseAuth mAuth;
    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        mAuth = FirebaseAuth.getInstance();

        v.findViewById(R.id.btn_logout).setOnClickListener(this);
        v.findViewById(R.id.btn_about).setOnClickListener(this);
        v.findViewById(R.id.btn_share).setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()) {
            case R.id.btn_about:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder
                        .setTitle("About")
                        .setMessage(getResources().getString(R.string.about_message))
                        .setCancelable(true)
                        .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                alertDialogBuilder.show();
                break;
            case R.id.btn_logout:
                mAuth.signOut();
                Intent intentLogOut = new Intent(getActivity(), MainActivity.class);
                intentLogOut.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intentLogOut.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intentLogOut.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentLogOut);
                break;
            case R.id.btn_share:
                Intent intentShare = new Intent(Intent.ACTION_SEND);
                intentShare.setType("text/plain");
                intentShare.putExtra(Intent.EXTRA_SUBJECT, "My Health Study App");
                intentShare.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.share_message));
                startActivity(Intent.createChooser(intentShare, "Share via"));
        }
    }
}
