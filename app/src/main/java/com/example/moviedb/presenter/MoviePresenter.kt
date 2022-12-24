package com.example.moviedb.presenter

interface MoviePresenter {
    fun getMovieList()
    fun onDestroy()
    fun onClear()
}