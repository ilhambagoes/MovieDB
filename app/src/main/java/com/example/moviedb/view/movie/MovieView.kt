package com.example.moviedb.view.movie

import com.example.moviedb.base.BaseView
import com.example.moviedb.model.MovieListModel

interface MovieView : BaseView {
    fun handleMovieList(movie : MovieListModel)
}