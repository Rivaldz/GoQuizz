package com.valdo.goquizz.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.valdo.goquizz.Adapter.SeePinAdapter;
import com.valdo.goquizz.R;
import com.valdo.goquizz.models.SeePinModel;

import java.util.ArrayList;
import java.util.List;

public class SeePinActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    List<SeePinModel>seePinModels = new ArrayList<>();

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_pin);
        recyclerView = findViewById(R.id.recyclerpinshow2);


        String username = ActivityLogin.emailLogin;
        String[] part = username.split("\\.");
        String usernameSplit = part[0];
        databaseReference = FirebaseDatabase.getInstance().getReference().child("PinGameUser").child(usernameSplit);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                SeePinModel seePinModel = dataSnapshot.getValue(SeePinModel.class);
                seePinModels.add(seePinModel);
                SeePinAdapter seePinAdapter = new SeePinAdapter(seePinModels);
                recyclerView.setAdapter(seePinAdapter);
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
}
