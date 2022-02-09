package com.example.twoactivitiesintentextras2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.twoactivitiesintentextras2.Passages1.passage_1
import com.example.twoactivitiesintentextras2.Passages1.passage_2
import com.example.twoactivitiesintentextras2.Passages1.passage_3

class ActivitySecond : AppCompatActivity() {
    private var choice = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second2)
        choice = intent.getIntExtra("number", 0)

        var textview = findViewById<TextView>(R.id.article_id)

        if(choice == 1) {
            textview.setText(passage_1)
        }
        else if(choice == 2) {
            textview.setText(passage_2)
        }
        else if(choice == 3) {
            textview.setText(passage_3)
        }
    }
}