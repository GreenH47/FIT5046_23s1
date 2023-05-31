package com.example.vitality;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

//import com.example.firebaseandroid.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    // Declare a FirebaseAuth object to handle user authentication
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize the FirebaseAuth object
        auth = FirebaseAuth.getInstance();

        // Find the register button, email EditText, and password EditText by ID
        Button registerButton = findViewById(R.id.addButton);
        EditText emailEditText = findViewById(R.id.emailEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);

        // Set an onClickListener for the register button
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text from the email and password EditTexts
                String email_txt = emailEditText.getText().toString();
                String password_txt = passwordEditText.getText().toString();

                // Check if either the email or password EditTexts are empty
                if (TextUtils.isEmpty(email_txt) || TextUtils.isEmpty(password_txt)) {
                    String msg = "Empty Username or Password";
                    toastMsg(msg);
                }
                // Check if password is too short
                else if (password_txt.length() < 6) {
                    String msg = "Password is too short";
                    toastMsg(msg);
                } else {
                    // If validation passes, register the user with the entered email and password
                    registerUser(email_txt, password_txt);
                }
            }
        });
    }

    // Method to register the user using FirebaseAuth
    private void registerUser(String email_txt, String password_txt) {
        // Use the FirebaseAuth object to create a new user with the given email and password
        auth.createUserWithEmailAndPassword(email_txt, password_txt)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String msg = "Registration Successful";
                            // If successful, start the MainActivity
                            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                        } else {
                            String msg = "Registration Unsuccessful";
                            toastMsg(msg);
                        }
                    }
                });
    }

    // Method to create and show a toast message
    public void toastMsg(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}