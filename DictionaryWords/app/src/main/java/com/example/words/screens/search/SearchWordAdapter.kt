package com.example.words.screens.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.words.R
import com.example.words.entity.Word

class SearchWordAdapter(
    private val suggestedWords: List<String>,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<SearchWordAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        val textView: TextView = view.findViewById(R.id.suggested_word)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.suggested_word_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = suggestedWords[position]
        holder.textView.text =item
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
    }

    override fun getItemCount() = suggestedWords.size

    class OnClickListener(val clickListener: (suggestedWord: String) -> Unit) {
        fun onClick(suggestedWord: String) = clickListener(suggestedWord)
    }
}