package com.loginext.casestudy.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.google.gson.Gson
import com.loginext.casestudy.R
import com.loginext.casestudy.databinding.HomeFragmentBinding
import com.loginext.casestudy.models.ApiResponse
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

    private lateinit var apiResponse: ApiResponse


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)

        // Only for Dev
        apiResponse = Gson().fromJson(readJsonFromAssets(), ApiResponse::class.java)
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

        bannerAdapter.reloadData(apiResponse.banners)
    }

    private fun getBannerPageTransform(): CompositePageTransformer {

        val offsetPx = resources.getDimensionPixelOffset(R.dimen.banner_page_offset)
        val marginPx = resources.getDimensionPixelOffset(R.dimen.banner_page_margin)

        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(marginPx))
        transformer.addTransformer { page, position ->
            if (position <= 0) {
                binding.bannerPager.setPadding(offsetPx, 0, 0, 0)
            } else {
                binding.bannerPager.setPadding(0, 0, offsetPx, 0)
            }
        }

        return transformer

    }

    private fun setupVoucherMessage() {
        binding.voucherMessage.text = "You have 5 voucher here"
    }


    private fun setupFoodCategoryView() {
        foodCategoryAdapter = FoodCategoryAdapter()

        binding.foodCategoriesView.apply {
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false).apply {
                stackFromEnd = true
            }
            adapter = foodCategoryAdapter
        }

        foodCategoryAdapter.reloadData(apiResponse.foodCategories)

    }

    private fun setupCollections() {
        collectionAdapter = CollectionAdapter()
        binding.collectionRView.apply {
            layoutManager = GridLayoutManager(requireActivity(), 2)
            adapter = collectionAdapter
            suppressLayout(true)
        }
        collectionAdapter.reloadData(apiResponse.offerCollection)
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

        restaurantListingAdapter.reloadData(apiResponse.restaurantCollections)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun readJsonFromAssets(): String {
        val apiResponse = resources.assets.open("api_response.json")
        val size = apiResponse.available()
        val buffer = ByteArray(size)
        apiResponse.read(buffer)
        apiResponse.close()
        return String(buffer, Charsets.UTF_8)
    }


}