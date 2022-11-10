package com.loginext.casestudy.data.models

import com.google.gson.annotations.SerializedName

data class OfferCollection(
    val background: String,
    val id: Int,
    val image: String,
    val name: String,
    
    @SerializedName("textcolor")
    val textColor: String
)