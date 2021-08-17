package com.backbase.assignment.data.remote.model

import com.google.gson.annotations.SerializedName

class MoviePageDTO {

    @SerializedName("page")
    var page: Int? = null;

    @SerializedName("results")
    var results: List<MovieDTO>? = null;

    @SerializedName("total_pages")
    var totalPages: Int? = null;

    @SerializedName("total_results")
    var totalResults: Int? = null;
}