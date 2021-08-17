package com.backbase.assignment.data.remote.model

import com.google.gson.annotations.SerializedName

class DatesDTO {
    @SerializedName("maximum")
    var maximum: String? = null;

    @SerializedName("minimum")
    var minimum: String? = null;
}