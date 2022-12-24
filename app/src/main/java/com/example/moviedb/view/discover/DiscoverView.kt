package com.example.moviedb.view.discover

import com.example.moviedb.base.BaseView
import com.example.moviedb.model.MovieListModel

interface DiscoverView : BaseView {
    fun handleMovieList(movie : MovieListModel)
}