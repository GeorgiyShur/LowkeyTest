package com.georgiyshur.lowkeytest.api

import com.google.gson.annotations.SerializedName

internal data class ApiPhotosResponse(
    @SerializedName("next_page")
    val nextPage: String?,
    val photos: List<ApiPhoto>,
)
