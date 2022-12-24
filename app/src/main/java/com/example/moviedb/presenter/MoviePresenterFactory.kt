package com.example.moviedb.presenter

import android.content.Context
import com.example.moviedb.repository.MovieRepositoryImpl
import com.example.moviedb.view.movie.MoviePresenterImpl
import com.example.moviedb.view.movie.MovieView

object MoviePresenterFactory {
    fun createMoviePresenter(context: Context, view: MovieView) : MoviePresenter {
        return MoviePresenterImpl(context, MovieRepositoryImpl(), view)
    }
}