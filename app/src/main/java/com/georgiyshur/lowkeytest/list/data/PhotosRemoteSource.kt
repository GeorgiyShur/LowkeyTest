package com.georgiyshur.lowkeytest.list.data

import com.georgiyshur.lowkeytest.api.ApiPhoto
import com.georgiyshur.lowkeytest.api.PhotosApi
import com.georgiyshur.lowkeytest.list.domain.Photo
import com.georgiyshur.lowkeytest.list.paging.PagedList
import javax.inject.Inject

internal const val PER_PAGE_DEFAULT = 15

internal interface PhotosRemoteSource {

    suspend fun fetchPhotos(
        page: Int?,
        perPage: Int,
    ): PagedList<Photo>
}

internal class PhotosRemoteSourceImpl @Inject constructor(
    private val api: PhotosApi,
) : PhotosRemoteSource {

    override suspend fun fetchPhotos(
        page: Int?,
        perPage: Int,
    ): PagedList<Photo> {
        val response = api.getPhotos(
            page = page,
            perPage = perPage,
        ).body() ?: return PagedList(emptyList(), null)
        return PagedList(
            data = response.photos.map { it.toPhoto() },
            nextPage = response.nextPage,
        )
    }

    private fun ApiPhoto.toPhoto(): Photo {
        return Photo(
            avgColor = avgColor,
            id = id,
            name = alt,
            photographer = photographer,
            url = src.original,
        )
    }
}