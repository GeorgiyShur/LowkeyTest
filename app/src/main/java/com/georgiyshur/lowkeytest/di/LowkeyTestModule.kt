package com.georgiyshur.lowkeytest.di

import com.georgiyshur.lowkeytest.data.PhotosRemoteSource
import com.georgiyshur.lowkeytest.data.PhotosRemoteSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface LowkeyTestModule {

    @Binds
    fun bindsPhotosRemoteDataSource(
        impl: PhotosRemoteSourceImpl,
    ): PhotosRemoteSource
}