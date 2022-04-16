package com.example.android.unscramble

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.android.unscramble.ui.game.allWordsList

class GameViewModel : ViewModel() {

    private fun getNextWord() {
        currentWord = allWordsList.random()
        val tempWord = currentWord.toCharArray()
        tempWord.shuffle()
        while(String(tempWord).equals(currentScrambledWord, false)) {
            tempWord.shuffle()
        }

    }

    init {
        Log.d("GameFragment", "GameViewModel created!")
    }

    private var score = 0
    private var currentWordCount = 0
    private var _currentScrambledWord = "test"
    val currentScrambledWord: String
        get() = _currentScrambledWord
    private var wordsList: MutableList<String> = mutableListOf()
    private lateinit var currentWord: String

    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment", "GameViewModel destroyed!")
    }


}