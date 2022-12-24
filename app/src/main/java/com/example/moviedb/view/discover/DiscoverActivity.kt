package com.example.moviedb.view.discover

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviedb.adapter.DiscoverAdapter
import com.example.moviedb.databinding.ActivityMainBinding
import com.example.moviedb.model.MovieGenre
import com.example.moviedb.model.MovieListModel
import com.example.moviedb.presenter.DiscoverPresenterFactory
import com.google.gson.Gson

class DiscoverActivity : AppCompatActivity(), DiscoverView {

    private lateinit var binding: ActivityMainBinding
    private val presenter by lazy { DiscoverPresenterFactory.createDiscoverPresenter(this) }
    private var movieGenre: MovieGenre? = null

    companion object {
        const val DATA_GENRE = "data_genre"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieGenre = Gson().fromJson(intent.getStringExtra(DATA_GENRE), MovieGenre::class.java)

        presenter.getMovieList(movieGenre?.id.toString())
    }

    override fun handleMovieList(movie: MovieListModel) {
        binding.rvMovies.apply {
            layoutManager = GridLayoutManager(this@DiscoverActivity, 3)
            adapter = DiscoverAdapter(movie.results)
        }
    }

    override fun onError(e: Throwable) {
        Log.getStackTraceString(e)
    }

    override fun onPause() {
        presenter.onClear()
        super.onPause()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}