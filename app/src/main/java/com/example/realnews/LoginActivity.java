package com.example.realnews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    TextView gotoRegister;
    EditText email1;
    EditText pass1;
    Button login;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        gotoRegister = findViewById(R.id.gotoRegister);
        gotoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
        login = findViewById(R.id.LoginBtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signin();
            }
        });
        email1 = findViewById(R.id.loginemail);
        pass1 = findViewById(R.id.loginpassword);

        Button email = (Button) findViewById(R.id.contactus);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.setType("message/rfc822");
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"jidugugowrisankar@gmail.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, "Welcome to CSE");
                email.putExtra(Intent.EXTRA_TEXT, "Please clear this and enter your issue so that we can have a look");
                startActivity(Intent.createChooser(email, "Choose Mail App"));

            }
        });
    }

    private void signin() {
        String email = email1.getText().toString();
        String password = pass1.getText().toString();
        if (TextUtils.isEmpty(email)) {
            email1.setError("Email cannot be empty");
            email1.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            pass1.setError("Password cannot be Empty");
            pass1.requestFocus();
        } else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this, "login successful", Toast.LENGTH_SHORT).show();
                        //startActivity(new Intent(MainActivity2.this,MainActivity3.class));
                    } else {
                        Toast.makeText(LoginActivity.this, "login failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}