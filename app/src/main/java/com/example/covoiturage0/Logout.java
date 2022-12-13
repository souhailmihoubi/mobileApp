package com.example.covoiturage0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Logout extends AppCompatActivity {

    Button logout;
    FirebaseAuth mailAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        logout = findViewById(R.id.logout);

        mailAuth = FirebaseAuth.getInstance();

        logout.setOnClickListener(view -> {
            mailAuth.signOut();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        });



    }
}