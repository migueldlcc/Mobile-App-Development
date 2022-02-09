package com.example.twoactivitiesintentextras1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.content.Intent
import android.widget.Button


class MainActivity : AppCompatActivity() {

    private val LOG_TAG = MainActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button_1 = findViewById<Button>(R.id.button1)
        val button_2 = findViewById<Button>(R.id.button2)
        val button_3 = findViewById<Button>(R.id.button3)


        button_1.setOnClickListener {
            launchSecondActvity(1)
        }

        button_2.setOnClickListener {
            launchSecondActvity(2)
        }

        button_3.setOnClickListener {
            launchSecondActvity(3)
        }
    }


    //Function to start the Second Activity
    fun launchSecondActvity(i: Int) {
        Log.d(LOG_TAG, "Button clicked!")
        val intent = Intent(this@MainActivity, SecondActivity::class.java)
        intent.putExtra("number", i)
        startActivity(intent)
    }




}