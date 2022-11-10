package com.loginext.casestudy.ui.home.adapters.viewholders

import android.app.Activity
import androidx.recyclerview.widget.LinearLayoutManager
import com.loginext.casestudy.databinding.RestaurantListingItemLayoutBinding
import com.loginext.casestudy.data.models.RestaurantCollection
import com.loginext.casestudy.ui.home.adapters.BaseAdapter
import com.loginext.casestudy.ui.home.adapters.RestaurantCardAdapter

class RestaurantCollectionViewHolder(private val activity: Activity, private val binding: RestaurantListingItemLayoutBinding) :
    BaseAdapter.BaseViewHolder<RestaurantCollection>(binding) {
    override fun bind(restaurantCollection: RestaurantCollection) {
        setupRestaurantListing(binding, restaurantCollection)
    }

    private fun setupRestaurantListing(binding: RestaurantListingItemLayoutBinding, restaurantCollection: RestaurantCollection) {

        binding.restaurantSectionTitle.text = restaurantCollection.name

        val restaurantCardAdapter = RestaurantCardAdapter()

        binding.restaurantsCardsList.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, true).apply {
                stackFromEnd = true
            }
            adapter = restaurantCardAdapter
        }
        restaurantCardAdapter.reloadData(restaurantCollection.restaurants)
    }
}