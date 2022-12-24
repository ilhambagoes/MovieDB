package com.example.moviedb.model

import com.google.gson.annotations.SerializedName

data class MovieReviewModel(

	@field:SerializedName("id")
	val id: Int = 0,

	@field:SerializedName("page")
	val page: Int = 0,

	@field:SerializedName("total_pages")
	val totalPages: Int = 0,

	@field:SerializedName("results")
	val results: List<ResultsRating>? = null,

	@field:SerializedName("total_results")
	val totalResults: Int = 0
)

data class AuthorDetails(

	@field:SerializedName("avatar_path")
	val avatarPath: String = "",

	@field:SerializedName("name")
	val name: String = "",

	@field:SerializedName("rating")
	val rating: Float? = null,

	@field:SerializedName("username")
	val username: String = ""
)

data class ResultsRating(

	@field:SerializedName("author_details")
	val authorDetails: AuthorDetails? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String = "",

	@field:SerializedName("author")
	val author: String = "",

	@field:SerializedName("created_at")
	val createdAt: String = "",

	@field:SerializedName("id")
	val id: String = "",

	@field:SerializedName("content")
	val content: String = "",

	@field:SerializedName("url")
	val url: String = ""
)
