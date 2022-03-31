package com.example.android.guesstheword.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class GameViewModel(sliderSeconds: Int) : ViewModel() {

    companion object {
        // Time when the game is over
        private const val DONE = 0L
        //Countdown time interval
        private const val ONE_SECOND = 1000L
        //Total time for the game
        private const val COUNTDOWN_TIME = 60000L
    }

//    private val _seconds = MutableLiveData<Int>()
//    val seconds: LiveData<Int>
//        get() = _seconds
//    init {
//        Log.i("ScoreViewModel", "Final score is $sliderSeconds")
//        _seconds.value = sliderSeconds
//    }
    // Countdown time
    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime


    // The String version of the current time
    val currentTimeString = Transformations.map(currentTime) {
        time -> DateUtils.formatElapsedTime(time)
    }
    private var timer: CountDownTimer?  = null

    // The current word
    private val _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word
    // The current score

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>
    // The current score
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score
    // Event which triggers the end of the game
    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish

    /**
     * Resets the list of words and randomizes the order
     */
    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }

    init {
        if (sliderSeconds > 1) {
            timer = object : CountDownTimer(sliderSeconds * 1000L, ONE_SECOND) {
                override fun onTick(millisUntilFinished: Long) {
                    _currentTime.value = millisUntilFinished/ ONE_SECOND
                }
                override fun onFinish() {
                    _currentTime.value = DONE
                    onGameFinish()
                }
            }
            timer?.start()
        }

        resetList()
        nextWord()
        _word.value = ""
        _score.value = 0
        Log.i("GameViewModel", "GameViewModel created!")

    }
    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {
        if (wordList.isEmpty()) {
            resetList()
        } else {
            //Select and remove a _word from the list
            _word.value = wordList.removeAt(0)
        }
    }
    /** Methods for buttons presses **/
    fun onSkip() {
        _score.value = (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = (score.value)?.plus(1)
        nextWord()
    }

    override fun onCleared() {
        super.onCleared()
        // Clear the timer
        timer?.cancel()
        Log.i("GameViewModel", "GameViewModel destroyed!")
    }


    fun onGameFinish() {
        _eventGameFinish.value = true
    }

    fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }
}

