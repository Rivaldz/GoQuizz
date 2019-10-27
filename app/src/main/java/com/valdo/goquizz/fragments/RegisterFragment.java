package com.valdo.goquizz.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.valdo.goquizz.MainActivity;
import com.valdo.goquizz.R;
import com.valdo.goquizz.activities.ActivityLogin;
import com.valdo.goquizz.models.RegisterModel;

import java.util.concurrent.Executor;

import static android.text.TextUtils.isEmpty;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegisterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class RegisterFragment extends Fragment {

    private static final  String TAG = MainActivity.class.getSimpleName();
    private OnFragmentInteractionListener mListener;
    private EditText nama;
    private EditText username;
    private EditText email;
    private EditText password;
    private EditText ulangiPassword;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private FirebaseAuth mFirebaseAuth;

    private String emailSt;
    private String passSt ;

    private String userId;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        nama = view.findViewById(R.id.editTextRegisterNama);
        username = view.findViewById(R.id.editTextRegisterUsername);
        email = view.findViewById(R.id.editTextRegisterEmail);
        password = view.findViewById(R.id.editTextRegisterPassword);
        ulangiPassword = view.findViewById(R.id.editTextRegisterUlangiPassword);
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();



        //get reference to 'user' node
        mFirebaseDatabase = mFirebaseInstance.getReference("users");
        //set app titile
        mFirebaseInstance.getReference("app_title").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e(TAG, "App titile update");

                String appTitle = dataSnapshot.getValue(String.class);

                // Update toolbar title not used
//                getSupportActionBar().setTitle(appTitle);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "Gagal membaca isi title app ", databaseError.toException());

            }
        });

        Button registerButton = view.findViewById(R.id.button_register);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailSt = email.getText().toString();
                passSt = password.getText().toString();
                if (!isEmpty(nama.getText().toString()) && !isEmpty(username.getText().toString()) && !isEmpty(email.getText().toString())
                    && !isEmpty(password.getText().toString()) && !isEmpty(ulangiPassword.getText().toString())
                        && (password.getText().toString().equalsIgnoreCase(ulangiPassword.getText().toString()))){

//                    userId = mFirebaseDatabase.push().getKey();
                    userId = username.getText().toString();

                    final RegisterModel registerModel = new RegisterModel(nama.getText().toString()
                    ,username.getText().toString(),email.getText().toString(),password.getText().toString());





//                    Intent intent = new Intent(getActivity(), ActivityLogin.class);
//                    startActivity(intent);

//                    Toast.makeText( getContext(), "Selamat Registrasi Anda Berhasil", Toast.LENGTH_LONG);

                    //input database

                }
                else {
                    @SuppressLint("WrongConstant") Snackbar snackBar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                            "Silahkan isi semua field dan password harus sama", Snackbar.LENGTH_SHORT);
                    snackBar.show();
                }
                mFirebaseAuth.createUserWithEmailAndPassword(emailSt, passSt)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
//                                    mFirebaseDatabase.child(userId).setValue(registerModel);
                                    FirebaseUser user = mFirebaseAuth.getCurrentUser();
                                    Intent i = new Intent(getActivity(), ActivityLogin.class);
                                    startActivity(i);
                                }
                                else {
                                    Toast.makeText(getContext(), "Gagal register",Toast.LENGTH_LONG);
                                }
                            }
                        });
            }
        });


        return view;


    }

    private void signUp(){
        password.getText().toString();
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
