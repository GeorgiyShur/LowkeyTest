package com.georgiyshur.lowkeytest.list.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class ListViewModel @Inject constructor(): ViewModel() {

    val photoItems = listOf(
        PhotoItem(
            author = "David Raya",
            imageUrl = "https://images.pexels.com/photos/3573351/pexels-photo-3573351.png",
            name = "Brown Rocks During Golden Hour",
        )
    )
}