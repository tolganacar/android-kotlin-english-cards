package com.tolganacar.englishcards.data.repo

import android.content.Context
import com.tolganacar.englishcards.data.model.EnglishWords

class WordRepository(context: Context) {
    private val sharedPrefs = context.getSharedPreferences("LearnedWords", Context.MODE_PRIVATE)

    fun getLearnedWords(): Set<String> {
        return sharedPrefs.getStringSet("learned_words", emptySet()) ?: emptySet()
    }

    fun markWordAsLearned(word: EnglishWords) {
        val editor = sharedPrefs.edit()
        val currentWords = sharedPrefs.getStringSet("learned_words", emptySet())?.toMutableSet()
        currentWords?.add(word.word)
        editor.putStringSet("learned_words", currentWords)
        editor.apply()
    }
}

