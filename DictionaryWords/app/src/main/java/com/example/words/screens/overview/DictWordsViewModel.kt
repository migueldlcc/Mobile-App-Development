package com.example.words.screens.overview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.words.database.WordDao
import com.example.words.entity.Word
import com.example.words.network.WordApiFilter

class DictWordsViewModel(
    private val dao: WordDao,
    application: Application
) : AndroidViewModel(application) {

    private var _currentFilter = MutableLiveData<WordApiFilter>()
    val currentFilter: LiveData<WordApiFilter>
        get() = _currentFilter


    var dictWords: LiveData<List<Word>>

    init {
        dictWords = dao.getActiveWords()
        _currentFilter.value = WordApiFilter.SHOW_ACTIVE
    }

    fun changeFilter(filter: WordApiFilter) {
        dictWords = when (filter) {
            WordApiFilter.SHOW_ALL -> dao.getAllWords()
            WordApiFilter.SHOW_ACTIVE -> dao.getActiveWords()
            else -> dao.getInactiveWords()
        }
        _currentFilter.value = filter
    }
}