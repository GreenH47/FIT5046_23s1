package com.example.fragmentjava;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.fragmentjava.databinding.ViewFragmentBinding;

// This is the class declaration for the ViewFragment class that extends Fragment
public class ViewFragment extends Fragment {

    // Declaration of the binding variable with ViewFragmentBinding data type
    private ViewFragmentBinding binding;

    // Constructor for the ViewFragment class
    public ViewFragment(){}

    // onCreateView() method, called when the view is created
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the View for this fragment using the binding
        binding = ViewFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Read the text message from SharedPreferences
        // Open the shared preferences for this app's named "Message" file.
        // This file is private and can't be accessed by other apps.
        SharedPreferences sharedPref= requireActivity().
                getSharedPreferences("Message", Context.MODE_PRIVATE);
        // Retrieve the string value associated with the key "message" from
        // the shared preferences file. If the value is not found or has been
        // removed, the default value is null.
        String message = sharedPref.getString("message", null);

        // Set text in textView to the retrieved message
        binding.textMessage.setText("Message from AddFragment: " + message);

        // Return the View object
        return view;
    }

    // onDestroyView() method, called when the fragment's view is destroyed
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Set binding variable to null to avoid memory leaks
        binding = null;
    }
}
