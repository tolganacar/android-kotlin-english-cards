package com.tolganacar.englishcards.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.tolganacar.englishcards.data.model.EnglishWords
import com.tolganacar.englishcards.databinding.LearnedWordListItemBinding
import com.tolganacar.englishcards.ui.fragment.LearnedWordListFragmentDirections

class LearnedWordListAdapter(var learnedWordList: List<EnglishWords>) :
    RecyclerView.Adapter<LearnedWordListAdapter.LearnedWordViewHolder>() {

    inner class LearnedWordViewHolder(var binding: LearnedWordListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LearnedWordViewHolder {
        val binding = LearnedWordListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LearnedWordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LearnedWordViewHolder, position: Int) {
        val learnedWord = learnedWordList[position]
        val t = holder.binding

        t.imageViewLearnedWordList.setImageResource(learnedWord.image)
        t.textViewLearnedWordList.text = learnedWord.word

        t.cardViewLearnedWordList.setOnClickListener {
            val action = LearnedWordListFragmentDirections.actionLearnedWordListFragmentToLearnedWordDetailFragment(learnedWord)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return learnedWordList.size
    }
}