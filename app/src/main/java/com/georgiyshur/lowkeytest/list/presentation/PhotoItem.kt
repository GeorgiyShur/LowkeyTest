package com.georgiyshur.lowkeytest.list.presentation

import androidx.compose.runtime.Immutable

@Immutable
internal data class PhotoItem(
    val author: String,
    val imageUrl: String,
    val name: String,
)
