package com.loginext.casestudy.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.loginext.casestudy.databinding.RestaurantCardLayoutBinding
import com.loginext.casestudy.models.Restaurant
import com.loginext.casestudy.ui.home.adapters.viewholders.RestaurantCardViewHolder

class RestaurantCardAdapter : BaseAdapter<RestaurantCardViewHolder, Restaurant>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantCardViewHolder {
        val binding = RestaurantCardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantCardViewHolder(binding)
    }

}