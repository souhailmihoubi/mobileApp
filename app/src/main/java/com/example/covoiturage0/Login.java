package com.example.covoiturage0;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText mail,password;
    Button loginbtn;
    FirebaseAuth mailAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //to Remove toolBar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);




        mail = findViewById(R.id.mail);
        password = findViewById(R.id.password);
        loginbtn = findViewById(R.id.loginbtn);

        mailAuth = FirebaseAuth.getInstance();

        loginbtn.setOnClickListener(view -> {
            loginUser();
        });

    }

    private void loginUser(){
        String email = mail.getText().toString();
        String pwd = password.getText().toString();

        if(TextUtils.isEmpty(email)){
            mail.setError("Mail cannot be empty!");
            mail.requestFocus();
        }
        else if(TextUtils.isEmpty(pwd)){
            password.setError("Password cannot be Empty!");
            password.requestFocus();
        }
        else{
            mailAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        //Toast.makeText(Login.this,"User logged successfully",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),Home.class));
                    }
                    else {
                        Toast.makeText(Login.this,"Error",Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
}