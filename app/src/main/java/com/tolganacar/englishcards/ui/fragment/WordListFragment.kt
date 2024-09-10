package com.tolganacar.englishcards.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tolganacar.englishcards.databinding.FragmentWordListBinding
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
        binding.recyclerViewWordList.adapter = adapterWordList
        binding.recyclerViewWordList.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observeLiveData() {
        viewModel.words.observe(viewLifecycleOwner) {
            it?.let {
                adapterWordList.wordList = it
                adapterWordList.notifyDataSetChanged()
            }
        }
    }

    private fun swipeRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshWords()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }
}