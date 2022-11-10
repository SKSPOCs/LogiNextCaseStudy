package com.loginext.casestudy.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import coil.load
import com.loginext.casestudy.databinding.CollectionItemLayoutBinding
import com.loginext.casestudy.data.models.OfferCollection

class CollectionAdapter :
    BaseAdapter<CollectionAdapter.CollectionItemViewHolder, OfferCollection>() {


    class CollectionItemViewHolder(private var binding: CollectionItemLayoutBinding) :
        BaseViewHolder<OfferCollection>(binding) {
        override fun bind(item: OfferCollection) {
            binding.collectionThumbnail.load(item.image)
            binding.collectionTitle.text = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionItemViewHolder {
        return CollectionItemViewHolder(
            CollectionItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}