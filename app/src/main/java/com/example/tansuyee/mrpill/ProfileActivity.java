package com.example.tansuyee.mrpill;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

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
                break;
            case 3:
                loadFragment(rewardsFragment);
                break;
            case 4:
                loadFragment(settingsFragment);
                break;
            default:
                loadFragment(homeFragment);
        }
    }

}
