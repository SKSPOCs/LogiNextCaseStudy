package com.loginext.casestudy.ui.home.adapters.viewholders

import android.graphics.Color
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import androidx.core.text.HtmlCompat.fromHtml
import androidx.core.view.setPadding
import coil.load
import com.loginext.casestudy.data.models.OfferCollection
import com.loginext.casestudy.data.models.Restaurant
import com.loginext.casestudy.databinding.RestaurantCardLayoutBinding
import com.loginext.casestudy.exts.imageGetter
import com.loginext.casestudy.ui.home.adapters.BaseAdapter

class RestaurantCardViewHolder(var binding: RestaurantCardLayoutBinding) : BaseAdapter.BaseViewHolder<Restaurant>(binding) {
    override fun bind(restaurant: Restaurant) {

        restaurant.additionalOffer?.let {
            binding.restaurantAdditionalOffer.apply {
                visibility = View.VISIBLE
                text = it
            }
        } ?: run { binding.restaurantAdditionalOffer.visibility = View.GONE }

        binding.restaurantImage.load(restaurant.imageUrl)

        binding.restaurantName.text = restaurant.name

        setTravelTimeAndRating(restaurant)
        setOffers(restaurant)
    }


    private fun setTravelTimeAndRating(restaurant: Restaurant) {
        val subTitleText = "${restaurant.displayDistance} \u25CF <img src=\"ic_star_rating\"> ${restaurant.rating}"

        binding.restaurantTravelTimeAndRating.text = fromHtml(
            subTitleText, FROM_HTML_MODE_LEGACY,
            { binding.root.context.imageGetter("ic_star_rating")!! }, null
        )
    }


    private fun setOffers(restaurant: Restaurant) {
        if (restaurant.offers.isEmpty()) {
            binding.chipGroup.visibility = View.GONE
            return
        }

        restaurant.offers.forEach {
            binding.chipGroup.addView(getChip(it))
        }


    }


    private fun getChip(offer: OfferCollection): TextView {

        val chipTv = TextView(binding.root.context)
        chipTv.text = " ${offer.name} "
        chipTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12.0f)
        chipTv.setPadding(10)
        chipTv.includeFontPadding = true

        offer.textColor?.let { chipTv.setTextColor(Color.parseColor("#$it")) }
        offer.background?.let { chipTv.setBackgroundColor(Color.parseColor("#$it")) }


//        val chip = Chip(binding.root.context)
//        chip.text = offer.name
//        chip.isCheckable = false
//        chip.isClickable = false
//        chip.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12.0f)
//        chip.chipCornerRadius = 0.0f
//        chip.setChipMinHeightResource(R.dimen.chip_min_height)
//        chip.setEnsureMinTouchTargetSize(false)
//
//        offer.textColor?.let { chip.setTextColor(Color.parseColor("#$it")) }
//        offer.background?.let { chip.chipBackgroundColor = ColorStateList.valueOf(Color.parseColor("#$it")) }
        return chipTv


    }

}