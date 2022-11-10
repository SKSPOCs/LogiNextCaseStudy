package com.loginext.casestudy.models

import com.google.gson.annotations.SerializedName

data class ApiResponse(
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
