package com.example.tansuyee.mrpill;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {

    public static final String EYE_UNLOCKED = "eyeUnlocked";
    public static final String PAIN_UNLOCKED = "painUnlocked";
    public static final String STOMACH_UNLOCKED = "stomachUnlocked";
    public static final String SKIN_UNLOCKED = "skinUnlocked";
    public static final String COMPLETED = "completedAll";
    private SharedPreferences mPreferences;

    private QuizzesFragment quizzesFragment;
    private HomeFragment homeFragment;
    private RewardsFragment rewardsFragment;
    private SettingsFragment settingsFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    loadFragment(homeFragment);
                    return true;
                case R.id.navigation_quizzes:
                    loadFragment(quizzesFragment);
                    return true;
                case R.id.navigation_rewards:
                    loadFragment(rewardsFragment);
                    return true;
                case R.id.navigation_settings:
                    loadFragment(settingsFragment);
                    return true;
            }
            return false;
        }
    };

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
            return true;
        }
        return false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        quizzesFragment = new QuizzesFragment();
        homeFragment = new HomeFragment();
        rewardsFragment = new RewardsFragment();
        settingsFragment = new SettingsFragment();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        switch (getIntent().getIntExtra("fragment", 0)) {
            case 2:
                loadFragment(quizzesFragment);
                navigation.setSelectedItemId(R.id.navigation_quizzes);
                break;
            case 3:
                loadFragment(rewardsFragment);
                navigation.setSelectedItemId(R.id.navigation_rewards);
                break;
            case 4:
                loadFragment(settingsFragment);
                navigation.setSelectedItemId(R.id.navigation_settings);
                break;
            default:
                loadFragment(homeFragment);
                navigation.setSelectedItemId(R.id.navigation_home);
        }

        mPreferences = getSharedPreferences("com.example.tansuyee.mrpill", MODE_PRIVATE);
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();

        if (getIntent().getExtras() != null) {
            String activityFrom = getIntent().getStringExtra("cleared");
            if (activityFrom.equals("1")) {
                preferencesEditor.putString(EYE_UNLOCKED, "eye_unlocked").apply();
            } else if (activityFrom.equals("2")) {
                preferencesEditor.putString(PAIN_UNLOCKED, "pain_unlocked").apply();
            } else if (activityFrom.equals("3")) {
                preferencesEditor.putString(STOMACH_UNLOCKED, "stomach_unlocked").apply();
            } else if (activityFrom.equals("4")) {
                preferencesEditor.putString(SKIN_UNLOCKED, "skin_unlocked").apply();
            } else if (activityFrom.equals("5")) {
                preferencesEditor.putString(COMPLETED, "completed_all").apply();
            }
        }

    }

}
