package com.example.vitality;


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

//base on the activity_login.xml finish the login page
public class LoginActivity extends AppCompatActivity{

    // Declare a FirebaseAuth object to handle user authentication
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize the FirebaseAuth object
        auth = FirebaseAuth.getInstance();

        // Find the login button, email EditText, and password EditText by ID
        Button loginButton = findViewById(R.id.login);
        Button signupButton = findViewById(R.id.signup);
        EditText emailEditText = findViewById(R.id.email);
        EditText passwordEditText = findViewById(R.id.password);

        // Set an onClickListener for the login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text from the email and password EditTexts
                String email_txt = emailEditText.getText().toString();
                String password_txt = passwordEditText.getText().toString();

                // Check if either the email or password EditTexts are empty
                if (email_txt.isEmpty() || password_txt.isEmpty()) {
                    String msg = "Empty Username or Password";
                    toastMsg(msg);
                }
                else {
                    // If validation passes, login the user with the entered email and password
                    loginUser(email_txt, password_txt);
                }
            }
        });
    }

    // This function creates a toast message with the given string parameter
    private void toastMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    // This function logs the user in with the given email and password
    private void loginUser(String email, String password) {
        // Use the FirebaseAuth object to sign in with the given email and password
        auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        // If login is successful, send the user to the MainActivity
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
    }

    //if login success then go to the homeactivity
    public void login(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}


