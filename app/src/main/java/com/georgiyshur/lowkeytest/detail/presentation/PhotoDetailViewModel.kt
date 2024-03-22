package com.georgiyshur.lowkeytest.detail.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.georgiyshur.lowkeytest.Destinations.Companion.ARG_PHOTO_ID
import com.georgiyshur.lowkeytest.detail.usecase.GetPhotoDetailUseCase
import com.georgiyshur.lowkeytest.domain.PhotoDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class PhotoDetailViewModel @Inject constructor(
    private val getPhotoDetail: GetPhotoDetailUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val mutableViewStateFlow = MutableStateFlow(State())
    val viewStateFlow get() = mutableViewStateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            val photoId: String = savedStateHandle.get<String>(ARG_PHOTO_ID) ?: return@launch
            try {
                updateState { copy(isLoading = true) }
                val photoDetail = getPhotoDetail.execute(photoId)
                updateState {
                    copy(
                        isLoading = false,
                        photoDetail = photoDetail,
                    )
                }
            } catch (e: Throwable) {
                updateState { copy(isLoading = false) }
                // Here we should handle and/or log the error
            }
        }
    }

    private fun updateState(updater: State.() -> State) {
        mutableViewStateFlow.update(updater)
    }

    data class State(
        val isLoading: Boolean = false,
        val photoDetail: PhotoDetail? = null,
    )
}