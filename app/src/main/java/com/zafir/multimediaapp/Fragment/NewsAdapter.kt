package com.zafir.multimediaapp.Fragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zafir.multimediaapp.News.Article
import com.zafir.multimediaapp.News.NewsData
import com.zafir.multimediaapp.R

class NewsAdapter(private val dogBreeds: List<Article>) :
    RecyclerView.Adapter<NewsAdapter.DogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news_choice, parent, false)
        return DogViewHolder(view)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.bind(dogBreeds[position])

    }

    override fun getItemCount(): Int{
        return dogBreeds.size
    }

    inner class DogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(breed: Article) {


            itemView.findViewById<TextView>(R.id.titleTextView).text = breed.title
            itemView.findViewById<TextView>(R.id.descriptionTextView).text = breed.description + "\r\n\n"

        }
    }

}