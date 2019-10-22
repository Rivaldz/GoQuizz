package com.valdo.goquizz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.valdo.goquizz.MainActivity;
import com.valdo.goquizz.R;

import static android.text.TextUtils.isEmpty;

public class LoginActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final EditText userName = view.findViewById(R.id.editTextLoginUsername);
        final EditText password = view.findViewById(R.id.editTextLoginPassword);
        setContentView(R.layout.activity_login);
        Button loginButton = findViewById(R.id.buttonLogin);
        TextView registerView = findViewById(R.id.textViewRegister);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isEmpty(userName.getText().toString()) && !isEmpty(password.getText().toString())){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LoginActivity.this, "Silahkan isi password dan username",Toast.LENGTH_LONG).show();
                }

            }
        });


        registerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(i);
            }
        });




    }
}
