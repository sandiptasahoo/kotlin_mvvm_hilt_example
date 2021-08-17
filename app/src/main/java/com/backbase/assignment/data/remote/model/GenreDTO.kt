package com.backbase.assignment.data.remote.model

import com.google.gson.annotations.SerializedName

class GenreDTO {
    @SerializedName("id")
    var id: Int? = null
    @SerializedName("name")
    var name: String? = null
}