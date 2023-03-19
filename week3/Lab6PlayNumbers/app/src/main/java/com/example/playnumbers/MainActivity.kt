package com.example.playnumbers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val randomButton = findViewById<Button>(R.id.randomButton)
        val incrementButton = findViewById<Button>(R.id.incrementButton)
        val textView = findViewById<TextView>(R.id.textView)
        textView.setBackgroundResource(android.R.color.holo_green_light)
//if you enter a value less than 10 below, the class will set it to 10 (min)
        val playNumber = PlayNumber(100)
        randomButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                textView.text = playNumber.random().toString()
            }
        })
        incrementButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                var temp=textView.text.toString().toInt()
                var incremented =playNumber.increment(temp)
                textView.text = incremented.toString()
            }
        })
    }
}
