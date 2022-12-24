package com.example.moviedb

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviedb.adapter.MovieAdapter
import com.example.moviedb.databinding.ActivityMainBinding
import com.example.moviedb.model.MovieListModel
import com.example.moviedb.presenter.MoviePresenterFactory
import com.example.moviedb.view.movie.MovieView

class MainActivity : AppCompatActivity(), MovieView {

    private lateinit var binding: ActivityMainBinding
    private val presenter by lazy { MoviePresenterFactory.createMoviePresenter(this, this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter.getMovieList()
    }

    override fun handleMovieList(movie: MovieListModel) {
        binding.rvMovies.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = MovieAdapter(movie.results)
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