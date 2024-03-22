package com.georgiyshur.lowkeytest.api

import com.google.gson.annotations.SerializedName

internal data class ApiPhoto(
    val alt: String,
    @SerializedName("avg_color")
    val avgColor: String,
    val id: String,
    val photographer: String,
    val src: Src,
) {

    internal data class Src(
        val original: String,
    )
}