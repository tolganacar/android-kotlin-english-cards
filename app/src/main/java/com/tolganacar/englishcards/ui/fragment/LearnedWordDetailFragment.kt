package com.tolganacar.englishcards.ui.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.tolganacar.englishcards.data.model.EnglishWords
import com.tolganacar.englishcards.databinding.FragmentLearnedWordDetailBinding
import com.tolganacar.englishcards.utils.animateFlip

class LearnedWordDetailFragment : Fragment() {
    private lateinit var binding: FragmentLearnedWordDetailBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLearnedWordDetailBinding.inflate(inflater, container, false)

        sharedPreferences = requireContext().getSharedPreferences("learned_words", Context.MODE_PRIVATE)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getWordDetail()
        unlearnButtonOnClick()
        imageViewAnimateFlip()
        imageViewAnimateFlipOnClick()
    }

    private fun getWordDetail() {
        val bundle: LearnedWordDetailFragmentArgs by navArgs()
        val word = bundle.learnedWord

        binding.textViewWordDetail.text = word.word
        binding.textViewTranslation.text = word.translation
        binding.textViewEnglishSentence.text = word.englishSampleSentence
        binding.textViewTurkishSentence.text = word.turkishSampleSentence
        binding.textViewPronunciationDetail.text = word.pronunciation
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

            Snackbar.make(requireView(), "Word unlearned!", Snackbar.LENGTH_SHORT).show()
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
}
