package com.georgiyshur.lowkeytest.list.presentation

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
internal data class PhotoItem(
    val avgColor: Color,
    val id: String,
    val name: String,
    val photographer: String,
    val url: String,
)
