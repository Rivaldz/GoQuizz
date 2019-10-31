package com.valdo.goquizz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.valdo.goquizz.R;

import org.w3c.dom.Text;

public class PlayQuiz extends AppCompatActivity {

    ImageView imageLoad;
    TextView quetsionLoad;
    Button answerA, answerB, anserC, answerD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_quiz);

        imageLoad = findViewById(R.id.imageViewPlayQuiz);
        quetsionLoad = findViewById(R.id.textViewPlayQuiz);
        answerA = findViewById(R.id.buttonAnswerA);
        answerB = findViewById(R.id.buttonAnswerB);
        anserC = findViewById(R.id.buttonAnswerC);
        answerD = findViewById(R.id.buttonAnswerD);


    }
}