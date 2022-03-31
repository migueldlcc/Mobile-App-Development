package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class GameViewModelFactory(private val sliderSeconds: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            Log.i("GameViewModel", "Current Time is $sliderSeconds")
            return GameViewModel(sliderSeconds) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}