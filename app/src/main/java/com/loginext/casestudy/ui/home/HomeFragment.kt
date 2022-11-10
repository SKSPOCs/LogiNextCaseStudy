package com.loginext.casestudy.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.loginext.casestudy.R
import com.loginext.casestudy.databinding.HomeFragmentBinding
import com.loginext.casestudy.ui.home.adapters.BannerAdapter
import com.loginext.casestudy.ui.home.adapters.CollectionAdapter
import com.loginext.casestudy.ui.home.adapters.FoodCategoryAdapter
import com.loginext.casestudy.ui.home.adapters.RestaurantsListingAdapter

class HomeFragment : Fragment() {

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var bannerAdapter: BannerAdapter
    private lateinit var foodCategoryAdapter: FoodCategoryAdapter
    private lateinit var collectionAdapter: CollectionAdapter
    private lateinit var restaurantListingAdapter: RestaurantsListingAdapter

    private val viewModel: HomeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        setupViews()
        return binding.root
    }


    private fun setupViews() {

        setupSearchView()
        setupBannerView()
        setupFoodCategoryView()
        setupVoucherMessage()
        setupCollections()
        setupRestaurantCollections()

        subscribeData()
        viewModel.getRestaurantData()


    }


    private fun subscribeData() {
        viewModel.banners.observe(requireActivity()) { bannerAdapter.reloadData(it) }
        viewModel.collections.observe(requireActivity()) { collectionAdapter.reloadData(it) }
        viewModel.foodCategory.observe(requireActivity()) { foodCategoryAdapter.reloadData(it) }
        viewModel.restaurantListing.observe(requireActivity()) { restaurantListingAdapter.reloadData(it) }
        viewModel.voucherCount.observe(requireActivity()) { setupVoucherMessage(it) }
    }


    private fun setupSearchView() {
        binding.searchView.setOnEditorActionListener { _, actionId, event ->

            when (actionId) {
                IME_ACTION_SEARCH, IME_ACTION_DONE, IME_ACTION_GO, IME_ACTION_NEXT -> {
                    binding.searchView.setText(event.displayLabel.toString())
                    // TODO ("API Call for search functionality")
                }
                else -> {}
            }
            true
        }
    }

    private fun setupBannerView() {
        bannerAdapter = BannerAdapter()

        binding.bannerPager.apply {
            adapter = bannerAdapter
            offscreenPageLimit = 2
            clipToPadding = false
            clipChildren = false

            setPageTransformer(getBannerPageTransform())


            getChildAt(0)?.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }
    }

    private fun getBannerPageTransform(): CompositePageTransformer {

        val offsetPx = resources.getDimensionPixelOffset(R.dimen.banner_page_offset)
        val marginPx = resources.getDimensionPixelOffset(R.dimen.banner_page_margin)

        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(marginPx))
        transformer.addTransformer { _, position ->
            if (position <= 0) {
                binding.bannerPager.setPadding(offsetPx, 0, 0, 0)
            } else {
                binding.bannerPager.setPadding(0, 0, offsetPx, 0)
            }
        }
        return transformer

    }

    private fun setupVoucherMessage(count: Int = 0) {
        binding.voucherMessage.text = "You have $count voucher here"
    }


    private fun setupFoodCategoryView() {
        foodCategoryAdapter = FoodCategoryAdapter()

        binding.foodCategoriesView.apply {
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false).apply {
                stackFromEnd = true
            }
            adapter = foodCategoryAdapter
        }


    }

    private fun setupCollections() {
        collectionAdapter = CollectionAdapter()
        binding.collectionRView.apply {
            layoutManager = GridLayoutManager(requireActivity(), 2)
            adapter = collectionAdapter
            suppressLayout(true)
        }
    }

    private fun setupRestaurantCollections() {
        restaurantListingAdapter = RestaurantsListingAdapter(requireActivity())

        binding.recommendedItemsView.apply {
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, true).apply {
                stackFromEnd = true
                isEnabled = false
                isNestedScrollingEnabled = true
            }
            adapter = restaurantListingAdapter
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}