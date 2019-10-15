package com.valdo.goquizz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.valdo.goquizz.activities.AddQuiz;
import com.valdo.goquizz.fragments.CreateQuizFragment;
import com.valdo.goquizz.fragments.EnterCodeFragment;
import com.valdo.goquizz.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity implements
    BottomNavigationView.OnNavigationItemSelectedListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(new HomeFragment());
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);

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
                Intent intent = new Intent(this, AddQuiz.class);
                startActivity(intent);
        }
        return loadFragment(fragment);
    }
}
