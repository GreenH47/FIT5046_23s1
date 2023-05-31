package com.example.firebaseandroid;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        // Find the TextView with ID "textView" and set its text to "This is HOME activity"
        TextView textview = findViewById(R.id.textView);
        textview.setText("This is HOME activity");
    }

}
