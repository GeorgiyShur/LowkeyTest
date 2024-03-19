package com.georgiyshur.lowkeytest.api

import okhttp3.Interceptor
import okhttp3.Response

internal class AuthInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", "3VvSuGv2zjumweS1IisHAZtITf7MnnrOuZrEIuM6G4FidOeo063N8nDu")
            .build()

        return chain.proceed(request)
    }
}