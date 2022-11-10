package com.loginext.casestudy.data.models

import com.google.gson.annotations.SerializedName

data class Restaurant(


    @SerializedName("display_distance")
    val displayDistance: String,

    val id: Int,

    @SerializedName("image_url")
    val imageUrl: String,

    val name: String,

    val offers: List<OfferCollection>,

    val rating: Double,

    @SerializedName("additional_offer")
    val additionalOffer: String?,
)