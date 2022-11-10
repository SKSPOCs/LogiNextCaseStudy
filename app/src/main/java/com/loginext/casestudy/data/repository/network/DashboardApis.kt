package com.loginext.casestudy.data.repository.network

import com.loginext.casestudy.data.models.DashboardResponse
import retrofit2.Response
import retrofit2.http.GET

interface DashboardApis {

    @GET("v1/0c5d380f-972a-44c9-bd11-ca5a2f154019")
    suspend fun getDashboardData(): Response<DashboardResponse>
}