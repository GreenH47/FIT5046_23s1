package com.example.firebaseandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    // Declare a FirebaseAuth object to handle user authentication
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the FirebaseAuth object
        auth = FirebaseAuth.getInstance();

        // Find the views using their IDs
        EditText emailEditText = findViewById(R.id.emailEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);
        Button registerButton = findViewById(R.id.signupButton);
        Button loginButton = findViewById(R.id.signinButton);

        // Set an onClickListener for the register button to open the SignupActivity
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignupActivity.class));
            }
        });

        // Set an onClickListener for the login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text from the email and password EditTexts
                String txt_Email = emailEditText.getText().toString();
                String txt_Pwd = passwordEditText.getText().toString();

                // Call the loginUser method with the entered email and password
                loginUser(txt_Email, txt_Pwd);
            }
        });

    }

    // Method to log in the user using FirebaseAuth
    private void loginUser(String txt_email, String txt_pwd) {
        // Use the FirebaseAuth object to sign in the user with the given email and password
        auth.signInWithEmailAndPassword(txt_email, txt_pwd)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        String msg = "Login Successful";
                        toastMsg(msg);
                        // If successful, start the HomeActivity
                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    }
                });
    }

    // Method to create and show a toast message
    public void toastMsg(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}

