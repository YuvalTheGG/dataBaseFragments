package com.example.databasefragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;


public class fragment1 extends Fragment implements View.OnClickListener{

    private EditText etId, etEmail;
    private Button signIn;
    private DatabaseHelper myDb;
    private View rootView;
    private CheckBox cb;
    public static final String SHARED_PREFS = "sharedPrefs";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_fragment1, container, false);
        myDb = new DatabaseHelper(getActivity());
        onCheck();

        etId = rootView.findViewById(R.id.idCheck);
        etEmail = rootView.findViewById(R.id.emailCheck);
        signIn = rootView.findViewById(R.id.sign_btn);
        myDb = new DatabaseHelper(getContext());

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myDb.dataExists(etId.getText().toString(), etEmail.getText().toString())) {
                    Toast.makeText(getContext(), "Sign in successful", Toast.LENGTH_SHORT).show();
                    etId.setText("");
                    etEmail.setText("");
                    cb = getView().findViewById(R.id.rememberMe);
                    cb.setChecked(false);
                } else {
                    Toast.makeText(getContext(), "Sign in failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onClick(View view) {

    }

    private void onCheck() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        String savedUsername = sharedPreferences.getString("username", "");

        if (!savedUsername.isEmpty()) {
            // If the username is saved, navigate to UserPage
            Intent go = new Intent(getContext(), ViewDataActivity.class);
            go.putExtra("UName", savedUsername);
            startActivity(go);
        }
    }
}