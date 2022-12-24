package com.example.moviedb.view.discover

import android.annotation.SuppressLint
import com.example.moviedb.presenter.DiscoverPresenter
import com.example.moviedb.repository.MovieRepository
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class DiscoverPresenterImpl(
    private val movieRepository: MovieRepository,
    private val discoverView: DiscoverView,
    private val disposable: CompositeDisposable = CompositeDisposable(),
    private val mainSchedulers: Scheduler = AndroidSchedulers.mainThread()
) : DiscoverPresenter {

    @SuppressLint("CheckResult")
    override fun getMovieList(movieId: String) {
        movieRepository.getMovieList(movieId)
            .observeOn(mainSchedulers)
            .subscribe ({
                discoverView.handleMovieList(it)
            }, {
                discoverView.onError(it)
            })
    }

    override fun onDestroy() {
        disposable.dispose()
    }

    override fun onClear() {
        disposable.clear()
    }
}