package com.example.moviedb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviedb.constant.Constant
import com.example.moviedb.databinding.RowItemDiscoverBinding
import com.example.moviedb.model.ResultsItem

class DiscoverAdapter(private val movieList: List<ResultsItem>) : RecyclerView.Adapter<DiscoverAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = RowItemDiscoverBinding.inflate(
        LayoutInflater.from(parent.context), parent, false).run {
            ViewHolder(this)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieList[position]
        holder.apply {
            Glide
                .with(itemView)
                .load(Constant.POSTER_PATH + movie.posterPath)
                .into(ivPoster)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    inner class ViewHolder(itemView : RowItemDiscoverBinding) : RecyclerView.ViewHolder(itemView.root){
        val ivPoster: ImageView = itemView.ivPoster
    }
}