package com.georgiyshur.lowkeytest.detail.usecase

import com.georgiyshur.lowkeytest.domain.PhotoDetail
import com.georgiyshur.lowkeytest.domain.PhotosRepository
import javax.inject.Inject

internal class GetPhotoDetailUseCase @Inject constructor(
    private val repository: PhotosRepository,
) {

    suspend fun execute(id: String): PhotoDetail = repository.fetchPhotoDetail(id)
}