package com.loginext.casestudy.ui.home.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import com.loginext.casestudy.databinding.RestaurantListingItemLayoutBinding
import com.loginext.casestudy.models.RestaurantCollection
import com.loginext.casestudy.ui.home.adapters.viewholders.RestaurantCollectionViewHolder

class RestaurantsListingAdapter(private val activity: Activity) : BaseAdapter<RestaurantCollectionViewHolder, RestaurantCollection>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantCollectionViewHolder {
        val binding = RestaurantListingItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantCollectionViewHolder(activity, binding)
    }

}