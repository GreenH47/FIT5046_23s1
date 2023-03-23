package com.example.fragmentjava;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.fragmentjava.databinding.AddFragmentBinding;

public class AddFragment extends Fragment {

    // Declaration of the addBinding variable with AddFragmentBinding data type
    private AddFragmentBinding addBinding;

    // Constructor for the AddFragment class
    public AddFragment(){}

    // onCreateView() method, called when the view is created
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the View for this fragment
        addBinding = AddFragmentBinding.inflate(inflater, container, false);

        // Create a View object from the inflated binding
        View view = addBinding.getRoot();

        // Set up an onClickListener for the addButton
        addBinding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the message string from editText
                String message = addBinding.editText.getText().toString();

                // If the message is not empty
                if (!message.isEmpty() ) {
                    // Store the message in SharedPreferences with a key "message"
                    SharedPreferences sharedPref= requireActivity().
                            getSharedPreferences("Message", Context.MODE_PRIVATE);
                    SharedPreferences.Editor spEditor = sharedPref.edit();
                    spEditor.putString("message", message);
                    spEditor.apply();
                }
            }
        });

        // Set up an onClickListener for the clearButton
        addBinding.clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear the editText view
                addBinding.editText.setText("");
            }
        });

        // Return the View object
        return view;
    }

    // onDestroyView() method, called when the fragment's view is destroyed
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Set addBinding variable to null to avoid memory leaks
        addBinding = null;
    }
}
