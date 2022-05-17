package com.pourkazemi.mahdi.maktab_hw_18_1.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class Interceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer aabcdefg")
            .build()

        return chain.proceed(newRequest)
    }
}