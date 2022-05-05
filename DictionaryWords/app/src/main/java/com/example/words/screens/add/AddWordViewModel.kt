package com.example.words.screens.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.words.database.WordDao
import com.example.words.entity.Word

class AddWordViewModel(
    val word: Word, val dao: WordDao, application: Application
) : AndroidViewModel(application) {

    suspend fun insert(word: Word) {
        dao.insertWord(word)
    }

    suspend fun update(dao: WordDao) {
        dao.updateWord(word)
    }
}