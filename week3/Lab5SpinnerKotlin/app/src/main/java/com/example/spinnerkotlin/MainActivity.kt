package com.example.spinnerkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.R
import com.example.spinnerkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.getRoot()
        setContentView(view)
        val list = ArrayList<String>()
        list.add("Toy Story")
        list.add("Up")
        list.add("Shrek")
        val spinnerAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,list)
        binding.movieSpinner.setAdapter(spinnerAdapter)
        binding.addButton.setOnClickListener(View.OnClickListener {
            val newMovie: String = binding.editText.text.toString()
            spinnerAdapter.add(newMovie)
            spinnerAdapter.notifyDataSetChanged()
            binding.movieSpinner.setSelection(spinnerAdapter.getPosition(newMovie))
        })
        binding.clearButton.setOnClickListener(View.OnClickListener {
            binding.editText.setText("")
        })
        binding.movieSpinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedMovie = parent?.getItemAtPosition(position).toString()
                val text = "Movie selected is $selectedMovie"
                Toast.makeText(applicationContext, text,Toast.LENGTH_LONG).show()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })
    }
}