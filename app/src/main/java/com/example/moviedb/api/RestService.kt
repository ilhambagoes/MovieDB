package com.example.moviedb.api

import com.example.moviedb.model.MovieDetailModel
import com.example.moviedb.model.MovieGenreResponse
import com.example.moviedb.model.MovieListModel
import com.example.moviedb.model.MovieReviewModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestService {

    @GET("genre/movie/list")
    fun getGenreList(): Observable<MovieGenreResponse>

    @GET("discover/movie")
    fun getDataMovies(
        @Query("with_genres") withGenreIds: String = ""
    ): Observable<MovieListModel>

    @GET("movie/{movieId}")
    fun getMovieDetailById(
        @Path("movieId") movieId: Int = 0
    ): Observable<MovieDetailModel>

    @GET("movie/{movieId}/reviews")
    fun getMovieReviews(
        @Path("movieId") movieId: Int = 0
    ): Observable<MovieReviewModel>

    @GET("movie/{movieId}/videos")
    fun getMovieTrailer(
        @Path("movieId") movieId: Int = 0
    )

}