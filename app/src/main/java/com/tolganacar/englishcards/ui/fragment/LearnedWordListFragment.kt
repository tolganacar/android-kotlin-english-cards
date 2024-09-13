package com.tolganacar.englishcards.ui.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.tolganacar.englishcards.data.model.EnglishWords
import com.tolganacar.englishcards.databinding.FragmentLearnedWordListBinding
import com.tolganacar.englishcards.ui.adapter.LearnedWordListAdapter

class LearnedWordListFragment : Fragment() {

    private lateinit var binding: FragmentLearnedWordListBinding
    private lateinit var adapterLearnedWords: LearnedWordListAdapter
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLearnedWordListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = requireActivity().getSharedPreferences("learned_words", Context.MODE_PRIVATE)

        setupRecyclerView()
        loadLearnedWords()
    }

    private fun setupRecyclerView() {
        adapterLearnedWords = LearnedWordListAdapter(emptyList())
        binding.recyclerViewLearnedWordList.apply {
            adapter = adapterLearnedWords
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun loadLearnedWords() {
        val learnedWords = getLearnedWordsFromPrefs()
        updateLearnedWordsList(learnedWords)
        handleEmptyState(learnedWords.isEmpty())
    }

    private fun getLearnedWordsFromPrefs(): List<EnglishWords> {
        val learnedWordsJson = sharedPreferences.getString("learned_words_list", null)
        return if (learnedWordsJson != null) {
            Gson().fromJson(learnedWordsJson, Array<EnglishWords>::class.java).toList()
        } else {
            emptyList()
        }
    }

    private fun updateLearnedWordsList(learnedWords: List<EnglishWords>) {
        adapterLearnedWords.learnedWordList = learnedWords
        adapterLearnedWords.notifyDataSetChanged()
    }

    private fun handleEmptyState(isEmpty: Boolean) {
        if (isEmpty) {
            binding.animationView.visibility = View.VISIBLE
            binding.textViewNoWords.visibility = View.VISIBLE
        } else {
            binding.animationView.visibility = View.GONE
            binding.textViewNoWords.visibility = View.GONE
        }
    }
}
