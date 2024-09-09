package com.tolganacar.englishcards.data.model

data class EnglishCards(val word: String,
                        val pronunciation: String,
                        val image: Int,
                        val translation: String,
                        val englishSampleSentence: String,
                        val turkishSampleSentence: String,
                        var learned: Boolean = false)
