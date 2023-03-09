package com.example.eventlistenerjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        * get a reference to the reverse button and then register it with OnClickListener.
        * For the onClick() method,the code below reverses any text the user enters in
        * the EditText (using the StringBuilder). You need to import libraries for Button,
        * EditText and View
        * */
        // Initialize reverse button and set click listener
        Button reverseButton = findViewById(R.id.reverse_button);
        reverseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.editMessage);
                String builder= new StringBuilder(editText.getText()).reverse().toString();
                editText.setText(builder);
            }
        });

        /*
        * clear_button so when it is clicked, the text in the edit text is cleared
        * (by setting the text to the empty string)
        * */
        // Initialize clear button and set click listener
        Button clearButton= findViewById(R.id.clear_button);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText =findViewById(R.id.editMessage);
                editText.setText("");
            }
        });
        /*
        * If replace with lambda function:
        * clearButton.setOnClickListener(v -> {
            EditText editText =findViewById(R.id.editMessage);
            editText.setText("");
           });
        * */



    }
}