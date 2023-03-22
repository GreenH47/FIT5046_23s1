package com.example.fragmentjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.example.fragmentjava.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // Declare an instance variable of the ActivityMainBinding class as a member field of the MainActivity class
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the layout for this activity using the provided LayoutInflater
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        // Set the content view of this activity to the root view of the inflated binding
        setContentView(view);

        // Set an onClickListener on the View button, which replaces the current fragment view with a new ViewFragment
        binding.viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new ViewFragment());
            }
        });

        // Set an onClickListener on the Add button, which replaces the current fragment view with a new AddFragment
        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new AddFragment());
            }
        });
    }

    // A private helper method used to replace the current fragment with a new fragment
    private void replaceFragment(Fragment nextFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Replace the fragment in the container view with the provided nextFragment
        fragmentTransaction.replace(R.id.fragment_container_view, nextFragment);

        // Commit the transaction to the back stack and execute it
        fragmentTransaction.commit();
    }
}
