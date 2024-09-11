package com.tolganacar.englishcards.ui.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.tolganacar.englishcards.databinding.FragmentWordDetailBinding
import com.tolganacar.englishcards.ui.viewmodel.WordListViewModel

class WordDetailFragment : Fragment() {
    private lateinit var binding: FragmentWordDetailBinding
    private lateinit var sharedPreferences: SharedPreferences
    private val viewModel: WordListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWordDetailBinding.inflate(inflater, container, false)

        sharedPreferences = requireContext().getSharedPreferences("learned_words", Context.MODE_PRIVATE)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getWordDetail()
        learnedButtonOnClick()
    }

    private fun getWordDetail() {
        val bundle: WordDetailFragmentArgs by navArgs()
        val word = bundle.word

        binding.textViewWordDetail.text = word.word
        binding.textViewTranslation.text = word.translation
        binding.textViewEnglishSentence.text = word.englishSampleSentence
        binding.textViewTurkishSentence.text = word.turkishSampleSentence
        binding.textViewPronunciationDetail.text = word.pronunciation
        binding.imageViewWordDetail.setImageResource(word.image)
    }

    private fun learnedButtonOnClick() {
        binding.buttonLearned.setOnClickListener {
            val bundle: WordDetailFragmentArgs by navArgs()
            val word = bundle.word

            val editor = sharedPreferences.edit()
            val learnedWordsSet = sharedPreferences.getStringSet("learned_words_set", mutableSetOf()) ?: mutableSetOf()
            learnedWordsSet.add(word.word)
            editor.putStringSet("learned_words_set", learnedWordsSet)
            editor.apply()

            viewModel.markWordAsLearned(word)

            Snackbar.make(requireView(), "Word learned!", Snackbar.LENGTH_SHORT).show()
        }
    }
}