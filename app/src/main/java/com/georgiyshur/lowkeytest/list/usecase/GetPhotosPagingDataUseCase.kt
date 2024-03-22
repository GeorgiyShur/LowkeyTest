package com.georgiyshur.lowkeytest.list.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.georgiyshur.lowkeytest.data.PER_PAGE_DEFAULT
import com.georgiyshur.lowkeytest.domain.Photo
import com.georgiyshur.lowkeytest.domain.PhotosRepository
import com.georgiyshur.lowkeytest.list.paging.PhotosPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GetPhotosPagingDataUseCase @Inject constructor(
    private val repository: PhotosRepository,
) {

    fun execute(): Flow<PagingData<Photo>> {
        val pagingSourceFactory = {
            PhotosPagingSource(
                repository = repository,
            )
        }

        return Pager(
            config = PagingConfig(pageSize = PER_PAGE_DEFAULT),
            pagingSourceFactory = pagingSourceFactory,
        ).flow
    }
}