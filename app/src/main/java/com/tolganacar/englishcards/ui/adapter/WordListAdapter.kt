package com.tolganacar.englishcards.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.tolganacar.englishcards.data.model.EnglishWords
import com.tolganacar.englishcards.databinding.WordListItemBinding
import com.tolganacar.englishcards.ui.fragment.WordListFragmentDirections

class WordListAdapter(var wordList: List<EnglishWords>) :
    RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    inner class WordViewHolder(var binding: WordListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val binding = WordListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        return WordViewHolder(binding)
    }


    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val word = wordList[position]
        val t = holder.binding

        t.imageViewWordList.setImageResource(word.image)
        t.textViewWordList.text = word.word

        t.cardViewWordList.setOnClickListener {
            val action = WordListFragmentDirections.actionWordListFragmentToWordDetailFragment(word)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return wordList.size
    }
}
