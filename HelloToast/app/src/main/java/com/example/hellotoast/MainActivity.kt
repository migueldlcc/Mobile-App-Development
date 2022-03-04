package com.example.hellotoast

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult

import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.view.View
import androidx.core.content.PackageManagerCompat

import androidx.core.content.PackageManagerCompat.LOG_TAG




class MainActivity : AppCompatActivity() {
    private var mCount = 0
    private lateinit var mShowCount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mShowCount = findViewById(R.id.show_count)
        findViewById<Button>(R.id.button_count).setOnClickListener{countUp()}

    }

    private fun showToast() {
        val toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT)
        toast.show()
    }

    private fun countUp() {
        mCount++
        mShowCount.text = mCount.toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("show_count", mShowCount.text.toString())
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        mShowCount.text = savedInstanceState.getString("show_count")
    }


    fun launchHelloActivity(view: View?) {
        val intent = Intent(this, HelloActivity::class.java)
        intent.putExtra("count", mShowCount.text)
        startActivity(intent)
    }
}