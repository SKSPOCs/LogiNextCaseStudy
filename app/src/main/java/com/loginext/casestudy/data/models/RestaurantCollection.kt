package com.loginext.casestudy.data.models

data class RestaurantCollection(
    val name: String,
    val priority: Int,
    val restaurants: List<Restaurant>
)