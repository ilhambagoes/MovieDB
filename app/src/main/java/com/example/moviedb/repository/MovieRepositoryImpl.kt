package com.example.moviedb.repository

import com.example.moviedb.api.RestServiceImpl
import com.example.moviedb.model.MovieGenreResponse
import com.example.moviedb.model.MovieListModel
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class MovieRepositoryImpl : MovieRepository {
    override fun getGenreList() : Observable<MovieGenreResponse> {
        return RestServiceImpl
            .getInstance()
            .getGenreList()
            .subscribeOn(Schedulers.io())
    }

    override fun getMovieList(genreId: String): Observable<MovieListModel> {
        return RestServiceImpl
            .getInstance()
            .getDataMovies(genreId)
            .subscribeOn(Schedulers.io())
    }
}