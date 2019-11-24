package com.valdo.goquizz.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.style.RelativeSizeSpan;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.valdo.goquizz.Adapter.RankAdapter;
import com.valdo.goquizz.R;
import com.valdo.goquizz.models.RankModel;
import com.valdo.goquizz.models.ResultTest;

import java.util.ArrayList;
import java.util.List;

public class RankActivity extends AppCompatActivity {
//    private FirebaseRecyclerAdapter adapter;
    RecyclerView recyclerViewRank;
    List<RankModel> rankModelList = new ArrayList<>();
    private ArrayList<String> emailUser = new ArrayList<String>();
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        recyclerViewRank = findViewById(R.id.listRankrecycler);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("ResultTest").child("38608");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                emailUser.add(dataSnapshot.getKey());
                RankModel showFirebase = dataSnapshot.getValue(RankModel.class);
                rankModelList.add(showFirebase);
                RankAdapter rankAdapter= new RankAdapter(rankModelList);
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



    }


}
