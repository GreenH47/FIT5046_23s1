package com.example.spinnerjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

// java/com/example/spinnerjava/MainActivity.java
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize buttons, edit text, and spinner
        Button addButton = findViewById(R.id.addButton);
        Button clearButton = findViewById(R.id.clearButton);
        EditText editText = findViewById(R.id.editText);
        Spinner movieSpinner = findViewById(R.id.movieSpinner);

        //initialize spinner list and adapter
        List<String> list = new ArrayList<String>();
        list.add("Toy Story");
        list.add("Up");
        list.add("Shrek");
        final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this ,android.R.layout.simple_spinner_item,
                list);
        movieSpinner.setAdapter(spinnerAdapter);

        //add button listener to add new movie to spinner
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String newMovie= editText.getText().toString();
                spinnerAdapter.add(newMovie);
                spinnerAdapter.notifyDataSetChanged();
                movieSpinner.setSelection(spinnerAdapter.getPosition(newMovie));
            }
        });

        //clear button listener to clear edit text
        clearButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                editText.setText("");
            }
        });

        //movie spinner listener to display selected movie in toast message
        // Each time an item is selected, a toast is displayed
        movieSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedMovie = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Movie selected is " + selectedMovie,Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}
