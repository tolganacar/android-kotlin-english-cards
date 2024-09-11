package com.tolganacar.englishcards.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tolganacar.englishcards.databinding.FragmentLearnedWordListBinding
import com.tolganacar.englishcards.ui.adapter.LearnedWordListAdapter
import com.tolganacar.englishcards.ui.adapter.WordListAdapter
import com.tolganacar.englishcards.ui.viewmodel.WordListViewModel

class LearnedWordListFragment : Fragment() {

    private lateinit var binding: FragmentLearnedWordListBinding
    private val viewModel: WordListViewModel by viewModels()
    private var adapterLearnedWords = LearnedWordListAdapter(emptyList())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLearnedWordListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeAdapter()
        observeLearnedWords()
    }

    private fun initializeAdapter() {
        binding.recyclerViewLearnedWordList.adapter = adapterLearnedWords
        binding.recyclerViewLearnedWordList.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observeLearnedWords() {
        val sharedPreferences = requireContext().getSharedPreferences("learned_words", Context.MODE_PRIVATE)
        val learnedWordsSet = sharedPreferences.getStringSet("learned_words_set", emptySet()) ?: emptySet()

        viewModel.learnedWords.observe(viewLifecycleOwner) { learnedWordList ->
            learnedWordList?.let {
                val filteredList = it.filter { word -> learnedWordsSet.contains(word.word) }
                adapterLearnedWords.learnedWordList = filteredList
                adapterLearnedWords.notifyDataSetChanged()
            }
        }
    }
}
