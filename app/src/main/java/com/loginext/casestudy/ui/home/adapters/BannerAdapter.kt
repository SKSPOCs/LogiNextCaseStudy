package com.loginext.casestudy.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import coil.load
import com.loginext.casestudy.data.models.Banner
import com.loginext.casestudy.databinding.BannerItemLayoutBinding

class BannerAdapter : BaseAdapter<BannerAdapter.BannerViewHolder, Banner>() {
    
    class BannerViewHolder(var bannerBinding: BannerItemLayoutBinding) : BaseViewHolder<Banner>(bannerBinding) {
        override fun bind(banner: Banner) {
            bannerBinding.bannerImg.load(banner.imageUrl)
            bannerBinding.bannerImg.tag = banner
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        return BannerViewHolder(
            BannerItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

}