package com.valdo.goquizz.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.valdo.goquizz.R;

import static android.text.TextUtils.isEmpty;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegisterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class RegisterFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private EditText nama;
    private EditText username;
    private EditText email;
    private EditText password;
    private EditText ulangiPassword;

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

        Button registerButton = view.findViewById(R.id.button_register);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!!isEmpty(nama.getText().toString()) && !isEmpty(username.getText().toString()) && !isEmpty(email.getText().toString())
                    && !isEmpty(password.getText().toString()) && !isEmpty(ulangiPassword.getText().toString())){

                   //input database

                }
                else {
                    Snackbar snackBar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                            "Silahkan isi semua field", Snackbar.LENGTH_LONG);
                    snackBar.show();
                }
            }
        });


        return view;
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
