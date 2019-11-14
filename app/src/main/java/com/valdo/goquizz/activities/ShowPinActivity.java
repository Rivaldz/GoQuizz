package com.valdo.goquizz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.valdo.goquizz.MainActivity;
import com.valdo.goquizz.R;

public class ShowPinActivity extends AppCompatActivity {
   private TextView pin ;
   private Button homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pin);
        pin = findViewById(R.id.pinNew);
        homeButton = findViewById(R.id.buttonHome);


        String pinString = String.valueOf(AddQuestion.userPin);
        pin.setText(pinString);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ShowPinActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
}
