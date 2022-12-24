package com.example.moviedb.presenter

interface DiscoverPresenter {
    fun getMovieList(movieId: String)
    fun onDestroy()
    fun onClear()
}