package com.tolganacar.englishcards.ui.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.tolganacar.englishcards.data.model.EnglishWords
import com.tolganacar.englishcards.databinding.FragmentLearnedWordListBinding
import com.tolganacar.englishcards.ui.adapter.LearnedWordListAdapter
import com.tolganacar.englishcards.ui.viewmodel.WordListViewModel

class LearnedWordListFragment : Fragment() {

    private lateinit var binding: FragmentLearnedWordListBinding
    private val viewModel: WordListViewModel by activityViewModels() // activityViewModels kullanarak aynı ViewModel'e erişiyoruz
    private lateinit var adapterLearnedWords: LearnedWordListAdapter

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
        adapterLearnedWords = LearnedWordListAdapter(emptyList()) // Başlangıçta boş bir liste veriyoruz
        binding.recyclerViewLearnedWordList.adapter = adapterLearnedWords
        binding.recyclerViewLearnedWordList.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observeLearnedWords() {
        val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("learned_words", Context.MODE_PRIVATE)
        val learnedWordsJson = sharedPreferences.getString("learned_words_list", null)
        if (learnedWordsJson != null) {
            val gson = Gson()
            val learnedWords: List<EnglishWords> = gson.fromJson(learnedWordsJson, Array<EnglishWords>::class.java).toList()
            adapterLearnedWords.learnedWordList = learnedWords
            adapterLearnedWords.notifyDataSetChanged()
        }
    }

}
