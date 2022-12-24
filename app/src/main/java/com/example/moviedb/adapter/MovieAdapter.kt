package com.example.moviedb.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviedb.R
import com.example.moviedb.constant.Constant
import com.example.moviedb.databinding.RowItemMovieBinding
import com.example.moviedb.model.MovieGenre
import com.example.moviedb.model.ResultsItem
import com.example.moviedb.utils.Tools
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class MovieAdapter(
    private val movieList: List<ResultsItem>,
    private val listener: OnItemListener
    ) : RecyclerView.Adapter<MovieAdapter.ViewHolder>(){

    private var genreList: MutableList<MovieGenre> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = RowItemMovieBinding.inflate(
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
            tvTitle.text = movie.title
            tvYear.text = Tools.convertDateText(movie.releaseDate, "yyyy", "yyyy-MM-dd")
            tvRating.text = movie.voteAverage.toString()

            genreList = Tools.loadData(holder.itemView.context).toMutableList()

            chipGenre.removeAllViews()
            movie.genreIds?.forEach {
                genreList.forEach { genre ->
                    if (it != null) {
                        if (it == genre.id) {
                            chipGenre.addView(createTagChip(itemView.context, genre.name))
                        }
                    }
                }
            }

            itemView.setOnClickListener {
                listener.onItemSelected(movie)
            }
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    inner class ViewHolder(itemView : RowItemMovieBinding) : RecyclerView.ViewHolder(itemView.root){
        val ivPoster: ImageView = itemView.ivPoster
        val tvTitle: TextView = itemView.tvTitle
        val tvYear: TextView = itemView.tvYear
        val tvRating: TextView = itemView.tvRating
        val chipGenre: ChipGroup = itemView.chipGenre
    }

    private fun createTagChip(context: Context, chipName: String): Chip {
        return Chip(context).apply {
            text = chipName
            tag = chipName
            isClickable = true
            setChipBackgroundColorResource(R.color.teal_700)
            setTextColor(ContextCompat.getColor(context, R.color.white))
            setOnClickListener {
                genreList.forEach {
                    if (it.name == text){
                        listener.onChipSelected(MovieGenre(it.id, it.name))
                    }
                }
            }
        }
    }

    interface OnItemListener {
        fun onItemSelected(item: ResultsItem)
        fun onChipSelected(genre: MovieGenre)
    }
}