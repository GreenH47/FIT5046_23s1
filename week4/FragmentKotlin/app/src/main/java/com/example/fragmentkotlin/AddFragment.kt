package com.example.fragmentkotlin

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragmentkotlin.databinding.AddFragmentBinding

//  accepting input from the user and then saving that input to SharedPreferences
class AddFragment : Fragment() {
    // Declare a private nullable variable to hold the ViewBinding.
    private var _binding: AddFragmentBinding? = null

    // Create a binding property that returns the binding or throws an exception if it's null.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the AddFragmentBinding and assign it to _binding.
        _binding = AddFragmentBinding.inflate(inflater, container, false)

        // Get the SharedPreferences instance for the "Message" key.
        val sharedPref: SharedPreferences = requireActivity(). getSharedPreferences(
            "Message",
            Context.MODE_PRIVATE
        )

        // Set an OnClickListener on the addButton to store the message in SharedPreferences.
        binding.addButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Get the message from the editText.
                val message: String = binding.editText.text.toString()

                // If the message is not empty, save it to SharedPreferences.
                if (message.isNotEmpty()) {
                    val spEditor = sharedPref.edit()
                    spEditor.putString("message", message)
                    spEditor.apply()
                }
            }
        })

        // Set an OnClickListener on the clearButton to clear the editText.
        binding.clearButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                binding.editText.setText("")
            }
        })

        // Return the root view of the binding.
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // Set the _binding variable to null to release the ViewBinding.
        _binding = null
    }
}
