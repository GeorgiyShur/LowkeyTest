package com.georgiyshur.lowkeytest.list.presentation

import androidx.compose.runtime.Immutable

@Immutable
internal data class PhotoItem(
    val id: String,
    val name: String,
    val photographer: String,
    val url: String,
)
