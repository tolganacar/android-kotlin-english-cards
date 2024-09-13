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
    private val args: WordDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWordDetailBinding.inflate(inflater, container, false)
        sharedPreferences = requireContext().getSharedPreferences("learned_words", Context.MODE_PRIVATE)
        tts = TextToSpeech(context, this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupListeners()
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts.language = Locale.US
        }
    }

    private fun setupUI() {
        val word = args.word

        with(binding) {
            textViewWordDetail.text = word.word
            textViewTranslation.text = word.translation
            textViewEnglishSentence.text = word.englishSampleSentence
            textViewTurkishSentence.text = word.turkishSampleSentence
            textViewPronunciationDetail.text = word.pronunciation
            textViewLevel.text = word.level

            textViewLevel.setTextColor(getLevelColor(word.level))
            imageViewWordDetail.setImageResource(word.image)
            imageViewWordDetail.animateFlip()
        }
    }

    private fun setupListeners() {
        binding.imageViewSpeaker.setOnClickListener {
            speakOut(binding.textViewWordDetail.text.toString())
        }

        binding.buttonLearned.setOnClickListener {
            markWordAsLearned(args.word)
        }

        binding.imageViewWordDetail.setOnClickListener {
            binding.imageViewWordDetail.animateFlip()
        }
    }

    private fun getLevelColor(level: String): Int {
        val colorRes = when (level) {
            "A1" -> R.color.a1
            "A2" -> R.color.a2
            "B1" -> R.color.b1
            "B2" -> R.color.b2
            "C1" -> R.color.c1
            "C2" -> R.color.c2
            else -> R.color.black
        }
        return ContextCompat.getColor(binding.root.context, colorRes)
    }

    private fun speakOut(text: String) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    private fun markWordAsLearned(word: EnglishWords) {
        val learnedWords = getLearnedWordsFromPrefs().toMutableList()

        if (!learnedWords.contains(word)) {
            learnedWords.add(word)
            saveLearnedWordsToPrefs(learnedWords)
            Snackbar.make(requireView(), "${word.word} learned!", Snackbar.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }
    }

    private fun getLearnedWordsFromPrefs(): List<EnglishWords> {
        val learnedWordsJson = sharedPreferences.getString("learned_words_list", null)
        return if (learnedWordsJson != null) {
            Gson().fromJson(learnedWordsJson, Array<EnglishWords>::class.java).toList()
        } else {
            emptyList()
        }
    }

    private fun saveLearnedWordsToPrefs(learnedWords: List<EnglishWords>) {
        val editor = sharedPreferences.edit()
        val updatedLearnedWordsJson = Gson().toJson(learnedWords)
        editor.putString("learned_words_list", updatedLearnedWordsJson)
        editor.apply()
    }

    override fun onDestroy() {
        super.onDestroy()
        tts.stop()
        tts.shutdown()
    }
}
