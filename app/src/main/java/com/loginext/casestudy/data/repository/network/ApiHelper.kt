package com.loginext.casestudy.data.repository.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiHelper {

    companion object {
        private const val BASE_URL = "https://mocki.io/"
        private val retrofit by lazy {
            val client = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor())
                .build()
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client).build()

        }

        val dashboardApis by lazy { retrofit.create(DashboardApis::class.java) }
    }
}