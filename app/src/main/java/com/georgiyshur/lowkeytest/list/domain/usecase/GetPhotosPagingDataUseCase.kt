package com.georgiyshur.lowkeytest.list.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.georgiyshur.lowkeytest.list.domain.Photo
import com.georgiyshur.lowkeytest.list.domain.PhotosRepository
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
            config = PagingConfig(pageSize = 15),
            pagingSourceFactory = pagingSourceFactory,
        ).flow
    }
}