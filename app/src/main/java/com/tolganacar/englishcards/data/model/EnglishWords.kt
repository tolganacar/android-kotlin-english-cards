package com.tolganacar.englishcards.data.model

import java.io.Serializable

data class EnglishWords(val word: String,
                        val pronunciation: String,
                        val image: Int,
                        val translation: String,
                        val englishSampleSentence: String,
                        val turkishSampleSentence: String,
                        val level: String,
                        var isLearned: Boolean = false) : Serializable
