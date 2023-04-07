package com.example.delivery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    Button loginBtn;
    EditText email, pwd;
    FirebaseAuth mAuth;
    TextView reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);






                email = findViewById(R.id.email);
                pwd = findViewById(R.id.pwd);
                reg = findViewById(R.id.reg);
                loginBtn = findViewById(R.id.loginBtn);

                mAuth = FirebaseAuth.getInstance();
                loginBtn.setOnClickListener(view -> {

                    loginUser();

                });
                reg.setOnClickListener(view ->
                {
                    startActivity(new Intent(Login.this, SignUp.class));
                });
            }


            private void loginUser(){
                String Email = email.getText().toString();
                String password = pwd.getText().toString();

                if (TextUtils.isEmpty(Email)){
                    email.setError("Email cannot be empty");
                    email.requestFocus();
                }else if (TextUtils.isEmpty(password)){
                    pwd.setError("Password cannot be empty");
                    pwd.requestFocus();
                }else{
                    mAuth.signInWithEmailAndPassword(Email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(Login.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                                Data.email = Email;
                                startActivity(new Intent(Login.this, Catalog.class));
                            }else{
                                Toast.makeText(Login.this, "Log in Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

    }
}