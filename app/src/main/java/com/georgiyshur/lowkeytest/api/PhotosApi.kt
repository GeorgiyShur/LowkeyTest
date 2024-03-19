package com.georgiyshur.lowkeytest.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface PhotosApi {

    @GET("curated")
    suspend fun getPhotos(
        @Query("page") page: Int?,
        @Query("perPage") perPage: Int,
    ): Response<ApiPhotosResponse>
}