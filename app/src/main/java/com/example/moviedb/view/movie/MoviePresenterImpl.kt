package com.example.moviedb.view.movie

import android.annotation.SuppressLint
import android.content.Context
import com.example.moviedb.presenter.MoviePresenter
import com.example.moviedb.repository.MovieRepository
import com.example.moviedb.utils.Tools
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class MoviePresenterImpl(
    private val context: Context,
    private val movieRepository: MovieRepository,
    private val movieView: MovieView,
    private val disposable: CompositeDisposable = CompositeDisposable(),
    private val mainSchedulers: Scheduler = AndroidSchedulers.mainThread()
) : MoviePresenter {

    @SuppressLint("CheckResult")
    override fun getMovieList() {
        movieRepository.getGenreList().flatMap {
            Tools.saveData(context, it.genres)
            movieRepository.getMovieList(it.genres?.forEach { movieGenre ->
                movieGenre.id.toString()
            }.toString())
        }
            .observeOn(mainSchedulers)
            .subscribe ({
                movieView.handleMovieList(it)
            }, {
                movieView.onError(it)
            })
    }

    override fun onDestroy() {
        disposable.dispose()
    }

    override fun onClear() {
        disposable.clear()
    }
}