package com.valdo.goquizz.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.valdo.goquizz.R;
import com.valdo.goquizz.activities.AddQuestion;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowPinFragment extends Fragment {
    private TextView pinFrag;


    public ShowPinFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_show_pin, container, false);
        pinFrag = view.findViewById(R.id.textViewpinFragment);
        int pin = AddQuestion.userPin;
        String userpine = String.valueOf(pin);
        pinFrag.setText(userpine);

        return  view;
    }

}
