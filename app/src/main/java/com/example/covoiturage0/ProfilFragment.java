package com.example.covoiturage0;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;


public class ProfilFragment extends Fragment {

    Button logout;
    FirebaseAuth mailAuth;

    TextView name,email;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_profil,container,false);

        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        logout = view.findViewById(R.id.logoutbtn);

        mailAuth = FirebaseAuth.getInstance();

        logout.setOnClickListener(view -> {
            mailAuth.signOut();
            startActivity(new Intent(getContext(),MainActivity.class));
        });


        return view;
    }
}