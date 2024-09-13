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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.tolganacar.englishcards.R
import com.tolganacar.englishcards.data.model.EnglishWords
import com.tolganacar.englishcards.databinding.FragmentLearnedWordDetailBinding
import com.tolganacar.englishcards.utils.animateFlip
import java.util.*

class LearnedWordDetailFragment : Fragment(), TextToSpeech.OnInitListener {
    private lateinit var binding: FragmentLearnedWordDetailBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var tts: TextToSpeech

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLearnedWordDetailBinding.inflate(inflater, container, false)

        sharedPreferences = requireContext().getSharedPreferences("learned_words", Context.MODE_PRIVATE)

        tts = TextToSpeech(context, this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getWordDetail()
        unlearnButtonOnClick()
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
        val bundle: LearnedWordDetailFragmentArgs by navArgs()
        val word = bundle.learnedWord

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

    private fun unlearnButtonOnClick() {
        binding.buttonUnlearned.setOnClickListener {
            val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("learned_words", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            val bundle: LearnedWordDetailFragmentArgs by navArgs()
            val word = bundle.learnedWord

            val gson = Gson()
            val learnedWordsJson = sharedPreferences.getString("learned_words_list", null)
            val learnedWords = if (learnedWordsJson != null) {
                gson.fromJson(learnedWordsJson, Array<EnglishWords>::class.java).toMutableList()
            } else {
                mutableListOf()
            }

            if (learnedWords.contains(word)) {
                learnedWords.remove(word)
                val updatedLearnedWordsJson = gson.toJson(learnedWords)
                editor.putString("learned_words_list", updatedLearnedWordsJson)
                editor.apply()
            }

            Snackbar.make(requireView(), "${word.word} unlearned!", Snackbar.LENGTH_SHORT).show()

            findNavController().navigateUp()
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
