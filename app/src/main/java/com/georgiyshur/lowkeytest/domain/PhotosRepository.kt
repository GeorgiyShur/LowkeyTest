package com.georgiyshur.lowkeytest.domain

import com.georgiyshur.lowkeytest.data.PhotosRemoteSource
import javax.inject.Inject

internal class PhotosRepository @Inject constructor(
    private val remoteSource: PhotosRemoteSource
) {

    suspend fun fetchPhotos(
        page: Int? = null,
        perPage: Int,
    ) = remoteSource.fetchPhotos(
        page = page,
        perPage = perPage,
    )

    suspend fun fetchPhotoDetail(id: String) = remoteSource.fetchPhotoDetail(id)
}
