package com.loginext.casestudy.data.repository

import com.loginext.casestudy.data.repository.network.ApiHelper

class DashboardRepository {

    suspend fun getDashboardData() = ApiHelper.dashboardApis.getDashboardData()
}