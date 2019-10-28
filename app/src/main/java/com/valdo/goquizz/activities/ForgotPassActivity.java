package com.valdo.goquizz.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.valdo.goquizz.R;

public class ForgotPassActivity extends AppCompatActivity {

   private EditText emailAuth ;
   private Button kirimBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        emailAuth = findViewById(R.id.editTextForgotPass);
        kirimBut = findViewById(R.id.buttonForgotPass);

        kirimBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().sendPasswordResetEmail(emailAuth.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Intent i = new Intent(ForgotPassActivity.this, ActivityLogin.class);
                                    startActivity(i);
                                    Toast.makeText(ForgotPassActivity.this, "Sukses reset password silahkan cek Email",Toast.LENGTH_LONG).show();
                                }
                                else {

                                    Toast.makeText(ForgotPassActivity.this, "Email Tidak Terdaftar ",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });


    }
}
