package com.example.fragmentkotlin

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragmentkotlin.databinding.ViewFragmentBinding

// display a message stored in SharedPreferences in a TextView.
class ViewFragment: Fragment() {
    // Declare a private nullable variable to hold the ViewBinding.
    private var _binding: ViewFragmentBinding? = null

    // Create a binding property that returns the binding or throws an exception if it's null.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the ViewFragmentBinding and assign it to _binding.
        _binding = ViewFragmentBinding.inflate(inflater, container, false)

        // Get the SharedPreferences instance for the "Message" key.
        val sharedPref: SharedPreferences = requireActivity().getSharedPreferences(
            "Message",
            Context.MODE_PRIVATE
        )

        // Get the value of the "message" key from SharedPreferences.
        val message = sharedPref.getString("message", null)

        // Set the text of the textMessage TextView to display the message from SharedPreferences.
        binding.textMessage.text = "Message from AddFragment: $message"

        // Return the root view of the binding.
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // Set the _binding variable to null to release the ViewBinding.
        _binding = null
    }
}
