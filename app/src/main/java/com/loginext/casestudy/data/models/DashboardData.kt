package com.loginext.casestudy.data.models

import com.google.gson.annotations.SerializedName

data class DashboardData(
    val banners: List<Banner>,
    @SerializedName("food_categories")
    val foodCategories: List<FoodCategory>,

    @SerializedName("number_of_active_vouchers")
    val activeVouchers: Int,

    @SerializedName("offer_collections")
    val offerCollection: List<OfferCollection>,

    @SerializedName("restaurant_collections")
    val restaurantCollections: List<RestaurantCollection>

)
