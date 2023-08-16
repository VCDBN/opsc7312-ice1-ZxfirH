package com.zafir.multimediaapp.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zafir.multimediaapp.News.NewsData
import com.zafir.multimediaapp.R

class NewsAdapter(private val dogBreeds: List<String>) :
    RecyclerView.Adapter<NewsAdapter.DogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news_choice, parent, false)
        return DogViewHolder(view)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.bind(dogBreeds[position])
    }

    override fun getItemCount(): Int = dogBreeds.size

    inner class DogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(breed: String) {
            itemView.findViewById<TextView>(R.id.dogBreedTextView).text = breed
        }
    }

}