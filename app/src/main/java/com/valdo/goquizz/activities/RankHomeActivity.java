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
import com.valdo.goquizz.Adapter.SeeRankAdapter;
import com.valdo.goquizz.MainActivity;
import com.valdo.goquizz.R;
import com.valdo.goquizz.fragments.EnterCodeFragment;
import com.valdo.goquizz.models.RankModel;
import com.valdo.goquizz.models.UserActivityModel;

import java.util.ArrayList;
import java.util.List;

public class RankHomeActivity extends AppCompatActivity {

    private Button buttonRankHome;
    RecyclerView recyclerViewRank;
    List<RankModel> rankModelList = new ArrayList<>();
    private ArrayList<String> emailUser = new ArrayList<String>();
    DatabaseReference databaseReference, fillUserActivity;
    FirebaseDatabase firebaseDatabase;

    String pinAll = null;
    String pinFromEnterCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank_home);
        buttonRankHome = findViewById(R.id.buttonRankHome);

        pinAll = SeeRankAdapter.pinRecycler;

        recyclerViewRank = findViewById(R.id.listRankrecycler);

        System.out.println("ini pin recyler dan oin all " + pinAll);

        pinFromEnterCode = String.valueOf(EnterCodeFragment.myPin);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("ResultTest").child(pinAll);
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
                startActivity(new Intent(RankHomeActivity.this, MainActivity.class));
            }
        });

    }

}
