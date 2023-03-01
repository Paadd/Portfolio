package com.msa.cityfy.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.msa.cityfy.R;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    String email;
    String password;
    EditText emailEditText;
    EditText passwordEditText;
    EditText confirmPasswordEditText;

    //  boolean loginyes=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button basl = findViewById(R.id.register_button);
        basl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passwordEditText.getText().toString().equals(confirmPasswordEditText.getText().toString())) {

                    email = emailEditText.getText().toString();
                    password = passwordEditText.getText().toString();
                    createAccount();

                }
            }
        });

        mAuth = FirebaseAuth.getInstance();


        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);


    }

    void createAccount() {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(MainActivity.TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(MainActivity.TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public void updateUI(FirebaseUser account) {
        if (account != null) {
            Toast.makeText(this, "U Registered In successfully", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, MenuActivity.class));
        } else {
            Toast.makeText(this, "U Didnt Register", Toast.LENGTH_LONG).show();
        }
    }
}