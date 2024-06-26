package com.georgiyshur.lowkeytest.list.presentation

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.georgiyshur.lowkeytest.domain.Photo
import com.georgiyshur.lowkeytest.list.usecase.GetPhotosPagingDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
internal class PhotosListViewModel @Inject constructor(
    private val getPhotosPagingData: GetPhotosPagingDataUseCase,
) : ViewModel() {

    val photosPagingData: Flow<PagingData<PhotoItem>> by lazy {
        getPhotosPagingData.execute()
            .map { photos -> photos.map { it.toPhotoItem() } }
            .cachedIn(viewModelScope)
    }

    private fun Photo.toPhotoItem(): PhotoItem {
        return PhotoItem(
            avgColor = Color(android.graphics.Color.parseColor(avgColor)),
            id = id,
            name = name,
            photographer = photographer,
            url = url,
        )
    }
}
