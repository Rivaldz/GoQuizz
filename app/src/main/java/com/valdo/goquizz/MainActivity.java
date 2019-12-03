package com.valdo.goquizz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.valdo.goquizz.Adapter.SeeRankAdapter;
import com.valdo.goquizz.activities.ActivityLogin;
import com.valdo.goquizz.activities.AddQuestion;
import com.valdo.goquizz.activities.AddQuiz;
import com.valdo.goquizz.activities.SeePinActivity;
import com.valdo.goquizz.activities.SeeRankActivity;
import com.valdo.goquizz.fragments.EnterCodeFragment;
import com.valdo.goquizz.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity implements
    BottomNavigationView.OnNavigationItemSelectedListener{

    private Button seeMyRank, seePInRankUser;
    private AdView adView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seeMyRank = findViewById(R.id.buttonLiharRank);
        seePInRankUser = findViewById(R.id.buttonLihatPin);

        loadFragment(new HomeFragment());
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        seeMyRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SeeRankActivity.class));
            }
        });

        seePInRankUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SeePinActivity.class));
            }
        });



    }
    private boolean loadFragment(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {}

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()){
            case R.id.action_home:
                fragment = new HomeFragment();
                break;
            case R.id.action_play:
                fragment = new EnterCodeFragment();
                break;
            case R.id.action_create:
                Intent intent = new Intent(this, AddQuestion.class);
                startActivity(intent);
        }
        return loadFragment(fragment);
    }
}
