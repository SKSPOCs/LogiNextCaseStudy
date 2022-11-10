package com.loginext.casestudy.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import coil.load
import com.loginext.casestudy.databinding.FoodCategoryItemLayoutBinding
import com.loginext.casestudy.models.FoodCategory

class FoodCategoryAdapter :
    BaseAdapter<FoodCategoryAdapter.FoodCategoryViewHolder, FoodCategory>() {

    class FoodCategoryViewHolder(var binding: FoodCategoryItemLayoutBinding) :
        BaseViewHolder<FoodCategory>(binding) {
        override fun bind(foodCategory: FoodCategory) {
            binding.foodCategoryIcon.load(foodCategory.icon)
            binding.foodCategoryName.text = foodCategory.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodCategoryViewHolder {
        val binding = FoodCategoryItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FoodCategoryViewHolder(binding)
    }

}