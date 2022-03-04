package com.example.hellotoast


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView



class HelloActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello)
       //choice = intent.getIntExtra("count", choice ).toString()
        val counter = intent.getStringExtra("count")
        var textview = findViewById<TextView>(R.id.display_count)
        textview.setText(counter)


    }
}