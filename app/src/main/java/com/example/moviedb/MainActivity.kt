package com.example.moviedb

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviedb.adapter.MovieAdapter
import com.example.moviedb.databinding.ActivityMainBinding
import com.example.moviedb.model.MovieGenre
import com.example.moviedb.model.MovieListModel
import com.example.moviedb.model.ResultsItem
import com.example.moviedb.presenter.MoviePresenterFactory
import com.example.moviedb.view.discover.DiscoverActivity
import com.example.moviedb.view.movie.MovieView
import com.google.gson.Gson

class MainActivity : AppCompatActivity(), MovieView, MovieAdapter.OnItemListener {

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
            adapter = MovieAdapter(movie.results, this@MainActivity)
        }
    }

    override fun onItemSelected(item: ResultsItem) {
        Log.d("DEVELOPER : ", Gson().toJson(item))
    }

    override fun onChipSelected(genre: MovieGenre) {
        startActivity(Intent(this@MainActivity, DiscoverActivity::class.java).apply {
            putExtra(DiscoverActivity.DATA_GENRE, Gson().toJson(genre))
        })
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