package com.valdo.goquizz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.valdo.goquizz.R;

public class itemActivity extends AppCompatActivity {
    Button buttonUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        buttonUser = findViewById(R.id.buttonItemSeeRankActivityzzzzz);

        buttonUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(Context.this, RankActivity.class));
            }
        });
    }
}
