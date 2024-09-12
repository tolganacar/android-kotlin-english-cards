package com.tolganacar.englishcards.ui.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.gson.Gson
import com.tolganacar.englishcards.data.model.EnglishWords
import com.tolganacar.englishcards.databinding.FragmentWordListBinding
import com.tolganacar.englishcards.ui.adapter.StackPageTransformer
import com.tolganacar.englishcards.ui.adapter.WordListAdapter
import com.tolganacar.englishcards.ui.viewmodel.WordListViewModel

class WordListFragment : Fragment() {

    private lateinit var binding: FragmentWordListBinding
    private val viewModel: WordListViewModel by viewModels()
    private var adapterWordList = WordListAdapter(emptyList())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWordListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeAdapter()
        observeLiveData()
        swipeRefresh()
    }

    private fun initializeAdapter() {
        binding.viewPagerWordList.adapter = adapterWordList
        binding.viewPagerWordList.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.viewPagerWordList.setPageTransformer(StackPageTransformer())
    }

    private fun observeLiveData() {
        viewModel.words.observe(viewLifecycleOwner) { wordList ->
            val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("learned_words", Context.MODE_PRIVATE)
            val learnedWordsJson = sharedPreferences.getString("learned_words_list", null)

            val learnedWords: List<EnglishWords> = if (learnedWordsJson != null) {
                Gson().fromJson(learnedWordsJson, Array<EnglishWords>::class.java).toList()
            } else {
                emptyList()
            }

            val filteredWords = wordList?.filterNot { learnedWords.contains(it) } ?: emptyList()

            adapterWordList.wordList = filteredWords
            adapterWordList.notifyDataSetChanged()
        }
    }

    private fun swipeRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshWords()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }
}