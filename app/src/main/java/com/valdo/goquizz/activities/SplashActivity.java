package com.valdo.goquizz.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.valdo.goquizz.R;
import com.valdo.goquizz.fragments.RegisterFragment;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Fragment fragment = new RegisterFragment();
        try {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e) {
            setContentView(R.layout.activity_splash);
        }
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

//                getSupportFragmentManager().beginTransaction().add(R.id.container, fragment).commit();
                startActivity(new Intent(SplashActivity.this, ActivityLogin.class));
                finish();
            }
        },1500);

    }
}
