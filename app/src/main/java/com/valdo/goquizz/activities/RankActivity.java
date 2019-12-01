package com.valdo.goquizz.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.valdo.goquizz.Adapter.RankAdapter;
import com.valdo.goquizz.MainActivity;
import com.valdo.goquizz.R;
import com.valdo.goquizz.fragments.EnterCodeFragment;
import com.valdo.goquizz.models.RankModel;
import com.valdo.goquizz.models.UserActivityModel;

import java.util.ArrayList;
import java.util.List;

public class RankActivity extends AppCompatActivity {
//    private FirebaseRecyclerAdapter adapter;
    private Button buttonRankHome;
    RecyclerView recyclerViewRank;
    List<RankModel> rankModelList = new ArrayList<>();
    private ArrayList<String> emailUser = new ArrayList<String>();
    DatabaseReference databaseReference, fillUserActivity;
    FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        buttonRankHome = findViewById(R.id.buttonRankHome);
        String pinRank = String.valueOf(EnterCodeFragment.myPin);

        recyclerViewRank = findViewById(R.id.listRankrecycler);


        databaseReference = FirebaseDatabase.getInstance().getReference().child("ResultTest").child(pinRank);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                emailUser.add(dataSnapshot.getKey());
                RankModel showFirebase = dataSnapshot.getValue(RankModel.class);
                rankModelList.add(showFirebase);
                RankAdapter rankAdapter = new RankAdapter(rankModelList,emailUser);
//                RankAdapter rankAdapter= new RankAdapter(rankModelList);
                //set adapter dan layputmanager
                recyclerViewRank.setAdapter(rankAdapter);
                System.out.println("halah ini show firebase " + showFirebase.getScoreuser());
                System.out.println("Ini adalah email dari user " + emailUser);
                //instansiasi
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

        recyclerViewRank.setLayoutManager(new LinearLayoutManager(this));

        buttonRankHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fillUserActivity();
                startActivity(new Intent(RankActivity.this, MainActivity.class));
            }
        });

    }

    private  void  fillUserActivity(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        fillUserActivity = firebaseDatabase.getReference("UserActivity");
        String emailUser = ActivityLogin.emailLogin;
        String[] part = emailUser.split("\\.");
        String emailSplit = part[0];
        String pinUser = String.valueOf(EnterCodeFragment.myPin);
        UserActivityModel userActivityModel = new UserActivityModel(pinUser);
        fillUserActivity.child(emailSplit).push().setValue(userActivityModel);
    }


}
