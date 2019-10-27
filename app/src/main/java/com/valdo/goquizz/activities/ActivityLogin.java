package com.valdo.goquizz.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.valdo.goquizz.MainActivity;
import com.valdo.goquizz.R;
import com.valdo.goquizz.fragments.RegisterFragment;

import static android.text.TextUtils.isEmpty;

public class ActivityLogin extends AppCompatActivity {

    EditText username,password;
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        username = findViewById(R.id.editTextLoginUsernameNew);
        password = findViewById(R.id.editTextLoginPasswordNew);
        register = findViewById(R.id.textViewRegister);
        Button loginBut = findViewById(R.id.buttonLoginNew);

        loginBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isEmpty(username.getText().toString()) && !isEmpty(password.getText().toString())){

                    Intent i = new Intent(ActivityLogin.this, MainActivity.class);
                    startActivity(i);

                }
                else {
                    @SuppressLint("WrongConstant") Snackbar snackbar1 = Snackbar.make(findViewById(R.id.fragment_container), "Isi semua field", Snackbar.LENGTH_LONG);
                    snackbar1.show();

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


    }
}
