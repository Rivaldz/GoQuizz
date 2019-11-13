package com.valdo.goquizz.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.valdo.goquizz.R;
import com.valdo.goquizz.fragments.EnterCodeFragment;
import com.valdo.goquizz.fragments.ReesultFragment;
import com.valdo.goquizz.fragments.RegisterFragment;
import com.valdo.goquizz.models.AddQuestionModel;
import com.valdo.goquizz.models.PlayQuizModel;
import com.valdo.goquizz.models.QuestionAnswerModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlayQuiz extends AppCompatActivity {

    private ImageView imageLoad;
    private TextView quetsionLoad;
    private Button answer1, answer2, answer3, answer4;

    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private  String userAnswer = null;
    private static String trueQuestions = null;
    private int indexSoal = 0;
    public static  int countQues = 0;
    public static int resultQuiz = 0;
//    public String = resultQuizz;
//    private List<QuestionAnswerModel> questionAnswerModelList;
    private ArrayList<String> userQuest = new ArrayList<String>();
    private ArrayList<String> userAnswer1= new ArrayList<String>();
    private ArrayList<String> userAnswer2= new ArrayList<String>();
    private ArrayList<String> userAnswer3= new ArrayList<String>();
    private ArrayList<String> userAnswer4= new ArrayList<String>();
    private ArrayList<String> trueQuestion= new ArrayList<String>();
    private QuestionAnswerModel questionAnswerModel;

    private Set<String> set = new HashSet<>(userQuest);


//    private pinInit initPin;

//    StorageReference storageReference;
//    String name =databaseReference.getKey();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_quiz);

//        initPin = new pinInit();

        imageLoad = findViewById(R.id.imageViewPlayQuiz);
        quetsionLoad = findViewById(R.id.textViewPlayQuiz);
        answer1 = findViewById(R.id.buttonAnswerA);
        answer2 = findViewById(R.id.buttonAnswerB);
        answer3= findViewById(R.id.buttonAnswerC);
        answer4 = findViewById(R.id.buttonAnswerD);
        final List<QuestionAnswerModel> playQuizList;

//        questionAnswerModelList
//        downloadImage();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        final PlayQuizModel input = new PlayQuizModel();

        playQuizList = new ArrayList<>();

        String pin = String.valueOf(EnterCodeFragment.myPin);
//        System.out.println("bababababababa dlfjaldfja;ldf jeijflakdjfiwe" + pin);
        databaseReference.child("BankSoal").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                countQues = (int) dataSnapshot.getChildrenCount();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        databaseReference.child("BankSoal").child(pin).addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                playQuizList.add(showQuestion);

//                String values = userQuest.get(1);
//                for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                countQues = (int) dataSnapshot.;

                    userQuest.add(dataSnapshot.getKey());
                    userQuest.addAll(set);
                    String value = dataSnapshot.getKey();
//                    quetsionLoad.setText(userQuest.get(1));
//                    System.out.println(value);
//                    childName = userQuest.get(1);
//                    downloadImage(userQuest.get(1));
//                    input.setQuestionQuizz(value);
                PlayQuizModel showQuestion = dataSnapshot.getValue(PlayQuizModel.class);

                    userAnswer1.add(showQuestion.getAnswer1());
                    userAnswer2.add(showQuestion.getAnswer2());
                    userAnswer3.add(showQuestion.getAnswer3());
                    userAnswer4.add(showQuestion.getAnswer4());
                    trueQuestion.add(showQuestion.getTrueAnswer());
//                answer1.setText(questionAnswerModel.getAnswer1());
//                answer2.setText(questionAnswerModel.getAnswer2());
//                answer3.setText(questionAnswerModel.getAnswer3());
//                answer4.setText(questionAnswerModel.getAnswer4());

//                quetsionLoad.setText(value);
                answer1.setText(userAnswer1.get(indexSoal));
                answer2.setText(userAnswer2.get(indexSoal));
                answer3.setText(userAnswer3.get(indexSoal));
                answer4.setText(userAnswer4.get(indexSoal));

//                playQuizList.add(new QuestionAnswerModel(input));

//                }


//                for(QuestionAnswerModel questionAnswerModel : playQuizList){
//                    int  i =0;
//                    i++;

//                childName = userQuest.get(1);
                downloadImage(userQuest.get(indexSoal));
                System.out.println( "ini adalah hasi list " +
                            " Jawaban 1 " +
                            userAnswer1+
                            "jawaban ke 2 " +
                            userAnswer2 +
                             "jawaban ke 3 " +
                            userAnswer3 +
                            "jawaban ke 4 " +
                            userAnswer4  +
                        "ini jawaban benar" +
                            trueQuestion
                        );
                trueQuestions = trueQuestion.get(indexSoal);

                System.out.println("percobaan menyimpan data " + userQuest);
                System.out.println("percobaan menyimpan data " + userQuest.get(indexSoal));
                quetsionLoad.setText(userQuest.get(0));




//
//                }


            }


            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                AddQuestionModel jawaban = dataSnapshot.child("Caknun kuyy").getValue(AddQuestionModel.class);
//                answer1.setText("NIM : "+jawaban.getAnswer1());
//                answer2.setText("Nama : "+jawaban.getAnswer2());
//                answer3.setText("Jurusan : "+jawaban.getAnswer3());
//                answer4.setText(jawaban.getAnswer4());

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




        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                onRestart();
//                indexSoal++;
                userAnswer = "A";
                getIndexSoal();
//                Toast.makeText(getBaseContext(), "Soal Selanjutnya",Toast.LENGTH_SHORT).show();
                quetsionLoad.setText(userQuest.get(indexSoal));
                downloadImage(quetsionLoad.getText().toString());
                System.out.println("ini hasil child bro iya pertanyaan " + userAnswer1);
                answer1.setText(userAnswer1.get(indexSoal));
                answer2.setText(userAnswer2.get(indexSoal));
                answer3.setText(userAnswer3.get(indexSoal));
                answer4.setText(userAnswer4.get(indexSoal));
//                answer1.setText(userAnswer1.get(1).indexOf(1));
//                getDataFirebase();

            }
        });

        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userAnswer = "B";
                getIndexSoal();
//                Toast.makeText(getBaseContext(), "Soal Selanjutnya",Toast.LENGTH_SHORT).show();
                quetsionLoad.setText(userQuest.get(indexSoal));
                downloadImage(quetsionLoad.getText().toString());
                answer1.setText(userAnswer1.get(indexSoal));
                answer2.setText(userAnswer2.get(indexSoal));
                answer3.setText(userAnswer3.get(indexSoal));
                answer4.setText(userAnswer4.get(indexSoal));

            }
        });

        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                indexSoal++;
                userAnswer = "C";
               getIndexSoal();
//                Toast.makeText(getBaseContext(), "Soal Selanjutnya",Toast.LENGTH_SHORT).show();
                quetsionLoad.setText(userQuest.get(indexSoal));
                downloadImage(quetsionLoad.getText().toString());
                answer1.setText(userAnswer1.get(indexSoal));
                answer2.setText(userAnswer2.get(indexSoal));
                answer3.setText(userAnswer3.get(indexSoal));
                answer4.setText(userAnswer4.get(indexSoal));
            }
        });

        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userAnswer = "D";
                getIndexSoal();
//                Toast.makeText(getBaseContext(), "Soal Selanjutnya",Toast.LENGTH_SHORT).show();
                quetsionLoad.setText(userQuest.get(indexSoal));
                downloadImage(quetsionLoad.getText().toString());
                answer1.setText(userAnswer1.get(indexSoal));
                answer2.setText(userAnswer2.get(indexSoal));
                answer3.setText(userAnswer3.get(indexSoal));
                answer4.setText(userAnswer4.get(indexSoal));
            }
        });




    }
    private int getIndexSoal(){
//        int userAns =
        if (indexSoal < countQues - 1){
           indexSoal++;
            Toast.makeText(getBaseContext(), "Soal Selanjutnya",Toast.LENGTH_SHORT).show();
            System.out.println("ini coount quest " + countQues);
            if (userAnswer.equalsIgnoreCase(trueQuestions)){
                resultQuiz++;

            }
//            trueQuestions = trueQuestion.get(indexSoal);
        }
        else {
            Fragment fragment = new ReesultFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container2, fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(null)
                    .commit();

            Toast.makeText(this, "Hasil Kuis Anda", Toast.LENGTH_LONG).show();

        }
        System.out.println("ini adalah result Quizz " + resultQuiz + "dan true question" + trueQuestions + " syukurin " + userAnswer);




        return indexSoal;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }


    private void getDataFirebase(){
        DatabaseReference zonesRef = FirebaseDatabase.getInstance().getReference("BankSoal");
        DatabaseReference zone1Ref = zonesRef.child("7420");
        DatabaseReference zone1NameRef = zone1Ref.child("Caknun kuyy");
        zone1NameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                dataSnapshot.getValue(PlayQuizModel.class);
//                for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                    AddQuestionModel jawaban = dataSnapshot.getValue(AddQuestionModel.class);
//                    answer1.setText("NIM : " + jawaban.getAnswer1());
//                    answer2.setText("Nama : " + jawaban.getAnswer2());
//                    answer3.setText("Jurusan : " + jawaban.getAnswer3());
//                    answer4.setText(jawaban.getAnswer4());
//                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void downloadImage(String name){
        StorageReference storageReference= FirebaseStorage.getInstance().getReference();
        StorageReference islandRef = storageReference.child("gambar/"+name+".jpg");

        final long ONE_MEGABYTE = 700 * 700;
        islandRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                // Data for "images/island.jpg" is returns, use this as needed
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                imageLoad.setImageBitmap(bmp);
            }
        })
                .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
                Toast.makeText(PlayQuiz.this, "Gagal mengambil soal, Silahkan cek koneksi",Toast.LENGTH_SHORT).show();
            }
        });
    }
}