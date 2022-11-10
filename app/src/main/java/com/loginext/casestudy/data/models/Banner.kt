package com.loginext.casestudy.data.models

import com.google.gson.annotations.SerializedName

data class Banner(
    val id: Int,

    @SerializedName("image_url")
    val imageUrl: String
)