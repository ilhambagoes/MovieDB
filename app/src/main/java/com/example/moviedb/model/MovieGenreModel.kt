package com.example.moviedb.model

import com.google.gson.annotations.SerializedName

data class MovieGenreResponse(
    @SerializedName("genres") var genres:List<MovieGenre>? = null
)

data class MovieGenre(
    @SerializedName("id") var id: Int = 0,
    @SerializedName("name") var name: String = ""
)