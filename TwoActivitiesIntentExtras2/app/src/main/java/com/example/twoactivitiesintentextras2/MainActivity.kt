package com.example.twoactivitiesintentextras2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)

        button1.setOnClickListener{
            launchActivitySecond(1)
        }

        button2.setOnClickListener{
            launchActivitySecond(2)
        }

        button3.setOnClickListener{
            launchActivitySecond(3)
        }
    }

    fun launchActivitySecond(i: Int) {
        val intent = Intent(this, ActivitySecond::class.java)
        intent.putExtra("number", i)
        startActivity(intent)
    }
}