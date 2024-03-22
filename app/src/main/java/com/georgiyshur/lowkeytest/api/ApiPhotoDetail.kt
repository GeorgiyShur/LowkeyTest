package com.georgiyshur.lowkeytest.api

internal data class ApiPhotoDetail(
    val alt: String,
    val photographer: String,
    val src: Src,
) {

    internal data class Src(
        val original: String,
    )
}