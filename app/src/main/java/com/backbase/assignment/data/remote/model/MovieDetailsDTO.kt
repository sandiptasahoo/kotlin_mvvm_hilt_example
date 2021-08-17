package com.backbase.assignment.data.remote.model

import com.google.gson.annotations.SerializedName
 
class MovieDetailsDTO {
    @SerializedName("backdrop_path")
    var backdrop_path: String? = null

    @SerializedName("genres")
    var genres: List<GenreDTO>? = null

    @SerializedName("id")
    var id: Int? = null

    @SerializedName("overview")
    var overview: String? = null

    @SerializedName("popularity")
    var popularity: Double? = null

    @SerializedName("poster_path")
    var poster_path: String? = null

    @SerializedName("release_date")
    var release_date: String? = null

    @SerializedName("runtime")
    var runtime: Int? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("vote_average")
    var vote_average: Double? = null

    @SerializedName("vote_count")
    var vote_count: Int? = null
}