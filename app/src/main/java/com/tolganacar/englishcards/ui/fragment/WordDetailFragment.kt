package com.tolganacar.englishcards.ui.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.tolganacar.englishcards.R
import com.tolganacar.englishcards.data.model.EnglishWords
import com.tolganacar.englishcards.databinding.FragmentWordDetailBinding
import com.tolganacar.englishcards.utils.animateFlip
import java.util.*

class WordDetailFragment : Fragment(), TextToSpeech.OnInitListener {
    private lateinit var binding: FragmentWordDetailBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var tts: TextToSpeech

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWordDetailBinding.inflate(inflater, container, false)

        sharedPreferences = requireContext().getSharedPreferences("learned_words", Context.MODE_PRIVATE)

        tts = TextToSpeech(context, this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getWordDetail()
        learnedButtonOnClick()
        imageViewAnimateFlip()
        imageViewAnimateFlipOnClick()

        binding.imageViewSpeaker.setOnClickListener {
            speakOut(binding.textViewWordDetail.text.toString())
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts.language = Locale.US
        }
    }

    private fun speakOut(text: String) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    private fun getWordDetail() {
        val bundle: WordDetailFragmentArgs by navArgs()
        val word = bundle.word

        binding.textViewWordDetail.text = word.word
        binding.textViewTranslation.text = word.translation
        binding.textViewEnglishSentence.text = word.englishSampleSentence
        binding.textViewTurkishSentence.text = word.turkishSampleSentence
        binding.textViewPronunciationDetail.text = word.pronunciation
        binding.textViewLevel.text = word.level
        val color = when (word.level) {
            "A1" -> R.color.a1
            "A2" -> R.color.a2
            "B1" -> R.color.b1
            "B2" -> R.color.b2
            "C1" -> R.color.c1
            "C2" -> R.color.c2
            else -> R.color.black
        }
        val resolvedColor = ContextCompat.getColor(binding.root.context, color)
        binding.textViewLevel.setTextColor(resolvedColor)
        binding.imageViewWordDetail.setImageResource(word.image)
    }

    private fun learnedButtonOnClick() {
        binding.buttonLearned.setOnClickListener {
            val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("learned_words", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            val bundle: WordDetailFragmentArgs by navArgs()
            val word = bundle.word

            val gson = Gson()
            val learnedWordsJson = sharedPreferences.getString("learned_words_list", null)
            val learnedWords = if (learnedWordsJson != null) {
                gson.fromJson(learnedWordsJson, Array<EnglishWords>::class.java).toMutableList()
            } else {
                mutableListOf()
            }

            if (!learnedWords.contains(word)) {
                learnedWords.add(word)
                val updatedLearnedWordsJson = gson.toJson(learnedWords)
                editor.putString("learned_words_list", updatedLearnedWordsJson)
                editor.apply()
            }

            Snackbar.make(requireView(), "Word learned!", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun imageViewAnimateFlip() {
        binding.imageViewWordDetail.animateFlip()
    }

    private fun imageViewAnimateFlipOnClick() {
        binding.imageViewWordDetail.setOnClickListener {
            binding.imageViewWordDetail.animateFlip()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (tts != null) {
            tts.stop()
            tts.shutdown()
        }
    }
}
