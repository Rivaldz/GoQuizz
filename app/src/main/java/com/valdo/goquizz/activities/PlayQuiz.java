package com.valdo.goquizz.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.valdo.goquizz.R;
import com.valdo.goquizz.models.AddQuestionModel;
import com.valdo.goquizz.models.PlayQuizModel;

import org.w3c.dom.Text;

public class PlayQuiz extends AppCompatActivity {

    ImageView imageLoad;
    TextView quetsionLoad;
    Button answerA, answerB, anserC, answerD;

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
//    StorageReference storageReference;
//    String name =databaseReference.getKey();


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
        downloadImage();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        databaseReference.child("BankSoal").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                PlayQuizModel showQuestion = dataSnapshot.getValue(PlayQuizModel.class);
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    String value = dataSnapshot.getKey();
                    quetsionLoad.setText(value);
                    System.out.println(value);

//                quetsionLoad.setText(value);
                answerA.setText(showQuestion.getAnswer1());
                answerB.setText(showQuestion.getAnswer2());
                anserC.setText(showQuestion.getAnswer3());
                answerD.setText(showQuestion.getAnswer4());

                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                for (DataSnapshot snap : dataSnapshot.getChildren()) {
//                    String value = snap.getValue(String.class);
//                    String key = snap.getKey();

//                for(DataSnapshot ds : dataSnapshot.getChildren()) {
//                    String name = ds.getKey();
//                    quetsionLoad.setText(name);
//                    System.out.println(name);
//                }

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.e("Data error", "erronya di", databaseError.toException());

            }
        });


    }

    private void downloadImage(){
        StorageReference storageReference= FirebaseStorage.getInstance().getReference();
        StorageReference islandRef = storageReference.child("gambar/980a6435-e32f-4f9f-ab52-193ffe7766df.jpg");

        final long ONE_MEGABYTE = 800 * 800;
        islandRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                // Data for "images/island.jpg" is returns, use this as needed
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                imageLoad.setImageBitmap(bmp);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
                Toast.makeText(PlayQuiz.this, "Gagal mengambil soal, Silahkan cek koneksi",Toast.LENGTH_SHORT).show();
            }
        });
    }
}