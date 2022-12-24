package com.example.moviedb.presenter

import com.example.moviedb.repository.MovieRepositoryImpl
import com.example.moviedb.view.discover.DiscoverPresenterImpl
import com.example.moviedb.view.discover.DiscoverView

object DiscoverPresenterFactory {
    fun createDiscoverPresenter(view: DiscoverView) : DiscoverPresenter {
        return DiscoverPresenterImpl(MovieRepositoryImpl(), view)
    }
}