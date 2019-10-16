package com.valdo.goquizz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.valdo.goquizz.R;

public class AddQuiz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quiz);
    }

    public void addQuizHandler(View view) {
        Intent intent = new Intent(this, AddQuestion.class);
        startActivity(intent);
    }
}
