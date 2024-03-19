package com.georgiyshur.lowkeytest.list.paging

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.georgiyshur.lowkeytest.list.domain.Photo
import com.georgiyshur.lowkeytest.list.domain.PhotosRepository

internal class PhotosPagingSource(
    private val repository: PhotosRepository,
) : PagingSource<Int, Photo>() {

    /**
     * We don't care about refresh key here since our data won't be invalidated.
     */
    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        return try {
            val page = params.key
            val perPage = params.loadSize
            val pagedList = repository.fetchPhotos(
                page = page,
                perPage = perPage,
            )
            /*
            This is a bit of a hack, but working with the nextKey as an URL is not
            very compatible with this approach and with how Retrofit works, so for
            the testing purposes we just extract the page number from the URL.
             */
            val nextPage = pagedList.nextPage?.let { nextPageUrl ->
                Uri.parse(nextPageUrl).getQueryParameter("page")?.toInt()
            }
            LoadResult.Page(
                data = pagedList.data,
                prevKey = null, // Not supporting loading previous pages.
                nextKey = nextPage,
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }
}