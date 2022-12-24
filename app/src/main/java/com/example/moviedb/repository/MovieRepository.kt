package com.example.moviedb.repository

import com.example.moviedb.model.MovieGenreResponse
import com.example.moviedb.model.MovieListModel
import io.reactivex.Observable

interface MovieRepository {
    fun getGenreList() : Observable<MovieGenreResponse>
    fun getMovieList(genreId: String) : Observable<MovieListModel>
}