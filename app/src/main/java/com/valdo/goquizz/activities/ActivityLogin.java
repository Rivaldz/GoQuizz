package com.valdo.goquizz.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.valdo.goquizz.MainActivity;
import com.valdo.goquizz.R;
import com.valdo.goquizz.fragments.RegisterFragment;

import static android.text.TextUtils.isEmpty;

public class ActivityLogin extends AppCompatActivity {

    EditText username,password;
    TextView register, forgotPass;
    private FirebaseAuth mAuth;
    private String usernameSt, passSt;
    public static String emailLogin = null;
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        username = findViewById(R.id.editTextLoginUsernameNew);
        password = findViewById(R.id.editTextLoginPasswordNew);
        register = findViewById(R.id.textViewRegister);
        mAuth = FirebaseAuth.getInstance();
        forgotPass = findViewById(R.id.textViewForgotPass);
        Button loginBut = findViewById(R.id.buttonLoginNew);
        adView = findViewById(R.id.iklanAdmobLogin);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        final String dataUsername = reference.child("users").child(username.getText().toString()).toString();
        final String dataPassword = reference.child("users").child(password.getText().toString()).toString();

        loginBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 usernameSt = username.getText().toString();
                 passSt = password.getText().toString();
                 emailLogin = usernameSt;
                 if (!isEmpty(usernameSt) && !isEmpty(passSt)) {
                     mAuth.signInWithEmailAndPassword(usernameSt, passSt)
                             .addOnCompleteListener(ActivityLogin.this, new OnCompleteListener<AuthResult>() {
                                 @Override
                                 public void onComplete(@NonNull Task<AuthResult> task) {
                                     if (task.isSuccessful()) {
                                         FirebaseUser user = mAuth.getCurrentUser();
                                         Intent i = new Intent(ActivityLogin.this, MainActivity.class);
                                         startActivity(i);
                                         finish();
                                     } else {
                                         Toast.makeText(ActivityLogin.this, "Gagal Login cek Username dan Password", Toast.LENGTH_LONG).show();
                                     }
                                 }
                             });
                 }else {
                     Toast.makeText(getBaseContext(), "Isi semua field", Toast.LENGTH_SHORT).show();
                 }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new RegisterFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment, fragment.getClass().getSimpleName())
                        .addToBackStack(null)
                        .commit();

            }
        });

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ActivityLogin.this, ForgotPassActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}
