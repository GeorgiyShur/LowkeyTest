package com.georgiyshur.lowkeytest.list.domain

import com.georgiyshur.lowkeytest.list.data.PER_PAGE_DEFAULT
import com.georgiyshur.lowkeytest.list.data.PhotosRemoteSource
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
}
