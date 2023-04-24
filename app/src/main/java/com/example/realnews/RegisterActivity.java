package com.example.realnews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    TextView gotoLogin;
    EditText fullname, Email, phno, pass;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    /*public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser== null){
            startActivity(new Intent(MainActivity.this,MainActivity2.class));
        }

    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        gotoLogin = findViewById(R.id.login);
        gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        mAuth = FirebaseAuth.getInstance();
        fullname = findViewById(R.id.fullname);
        Email = findViewById(R.id.email);
        phno = findViewById(R.id.phoneno);
        pass = findViewById(R.id.password);

        Button btn = findViewById(R.id.Register);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount();
                String s1 = fullname.getText().toString();
                String s2 = Email.getText().toString();
                String s3 = phno.getText().toString();
                String s4 = pass.getText().toString();

                writeNewUser(s1, s2, s3, s4);
            }
        });
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    private void createAccount() {
        String email = Email.getText().toString();
        String password = pass.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Email.setError("Email cannot be empty");
            Email.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            pass.setError("Password cannot be Empty");
            pass.requestFocus();
        } else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        Toast.makeText(RegisterActivity.this, "email and password Registered", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    public void writeNewUser(String username, String password, String email, String phonenumber) {
        User user = new User(username, password, email, phonenumber);
        mDatabase.child("Users").child(username).setValue(user);
    }


}
