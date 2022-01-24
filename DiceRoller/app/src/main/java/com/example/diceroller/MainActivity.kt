package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.roll_button)
        rollButton.setOnClickListener { rollDice() }
        val countButton: Button = findViewById(R.id.countup_button)
        countButton.setOnClickListener { countUp() }
        val resetButton: Button = findViewById(R.id.reset_button)

        resetButton.setOnClickListener {
            resetButton.text = "0"
        }
    }

    private fun rollDice() {
        val randomInit = (1..6).random()
        val resultText: TextView = findViewById(R.id.result_text)
        resultText.text = randomInit.toString()
    }

    private fun countUp() {
        val resultText: TextView = findViewById(R.id.result_text)

        if (resultText.text == "Hello World!") {
            resultText.text = "1"
        }
        else {
            var resultInt = resultText.text.toString().toInt()
            if (resultInt < 6) {
                resultInt ++
                resultText.text = resultInt.toString()
            }
        }
    }

}