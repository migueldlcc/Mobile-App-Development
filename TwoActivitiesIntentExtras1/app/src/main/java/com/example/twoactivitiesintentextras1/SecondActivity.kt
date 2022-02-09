package com.example.twoactivitiesintentextras1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

import com.example.twoactivitiesintentextras1.passages.passage_2
import com.example.twoactivitiesintentextras1.passages.passage_1
import com.example.twoactivitiesintentextras1.passages.passage_3

class SecondActivity : AppCompatActivity() {
    private var choice = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        choice = intent.getIntExtra("number", 0)

        var textView = findViewById<TextView>(R.id.article_id)

        if(choice == 1) {
            textView.setText(passage_1)
        }
        else if(choice == 2) {
            textView.setText(passage_2)
        }
        else if(choice == 3) {
            textView.setText(passage_3)
        }




    }





}

