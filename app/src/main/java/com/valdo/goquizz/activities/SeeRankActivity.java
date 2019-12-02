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
import com.valdo.goquizz.Adapter.SeeRankAdapter;
import com.valdo.goquizz.R;
import com.valdo.goquizz.fragments.EnterCodeFragment;
import com.valdo.goquizz.models.RankModel;
import com.valdo.goquizz.models.SeeRankModel;

import java.util.ArrayList;
import java.util.List;

public class SeeRankActivity extends AppCompatActivity {
    Button temButton;

    private RecyclerView recyclerView;
    List<SeeRankModel>rankModels = new ArrayList<>();

    DatabaseReference databaseReference;

    public String pin = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_rank);
        temButton = findViewById(R.id.buttonItemSeeRankActivity);
        recyclerView = findViewById(R.id.seeRankRecycler);

        EnterCodeFragment.myPin = 0;

        String username = ActivityLogin.emailLogin;
        String[] part = username.split("\\.");
        String usernameSplit = part[0];
        databaseReference = FirebaseDatabase.getInstance().getReference().child("UserActivity").child(usernameSplit);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                SeeRankModel seeRankModel = dataSnapshot.getValue(SeeRankModel.class);
                System.out.println("ini adalah segala sesuatu yang sangat luar binasa hehehe " + seeRankModel.getPinUserActivity());
                rankModels.add(seeRankModel);
                SeeRankAdapter rankAdapter = new SeeRankAdapter(rankModels);
                recyclerView.setAdapter(rankAdapter);

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
        recyclerView.setLayoutManager(new LinearLayoutManager(this));




    }

    private void retriveResultTest(){
//        databaseReference = FirebaseDatabase.getInstance().getReference().child("ResultTest").child(pin);
//        databaseReference.addChildEventListener(new )
    }
}
