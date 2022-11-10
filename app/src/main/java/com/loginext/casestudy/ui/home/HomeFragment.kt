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

    // Double binding variable avoid null check
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    // multiple list adapters
    private lateinit var bannerAdapter: BannerAdapter
    private lateinit var foodCategoryAdapter: FoodCategoryAdapter
    private lateinit var collectionAdapter: CollectionAdapter
    private lateinit var restaurantListingAdapter: RestaurantsListingAdapter

    // viewModel to access functionalities.
    // Need to use fragment-ktx dependency. It reduce too much code in the application
    private val viewModel: HomeViewModel by viewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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

        // Observe Data from api
        subscribeData()

        // calling restaurant data through api using viewModel
        viewModel.getRestaurantData()
    }

    /**
     * Observing all the data using liveData from viewModel
     */
    private fun subscribeData() {
        viewModel.banners.observe(requireActivity()) { bannerAdapter.reloadData(it) }
        viewModel.collections.observe(requireActivity()) { collectionAdapter.reloadData(it) }
        viewModel.foodCategory.observe(requireActivity()) { foodCategoryAdapter.reloadData(it) }
        viewModel.restaurantListing.observe(requireActivity()) { restaurantListingAdapter.reloadData(it) }
        viewModel.voucherCount.observe(requireActivity()) { setupVoucherMessage(it) }
    }

    /**
     * Search View basic functionality to that it can be extend when search button pressed on keyboard
     */
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

    /**
     * Top Banner with ViewPager2 two.
     */
    private fun setupBannerView() {
        bannerAdapter = BannerAdapter()

        binding.bannerPager.apply {
            adapter = bannerAdapter
            offscreenPageLimit = 2
            clipToPadding = false
            clipChildren = false
            setPageTransformer(getBannerPageTransform())

            // showing first element at the first time when screen loads.
            getChildAt(0)?.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }
    }

    /**
     * Transformation used to overlap second banner on the screen.
     */
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

    /**
     * Setting up voucher message with count.
     */
    private fun setupVoucherMessage(count: Int = 0) {
        binding.voucherMessage.text = "You have $count voucher here"
    }


    /**
     * Setting up food categories using Horizontal RecyclerView
     */
    private fun setupFoodCategoryView() {
        foodCategoryAdapter = FoodCategoryAdapter()

        binding.foodCategoriesView.apply {
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
            adapter = foodCategoryAdapter
        }


    }

    /**
     * Setting up Offer Collections using RecyclerView with Grid Layout to show 2 Column view.
     * GridLayout and GridView also can be used but it adds additional complexity
     */
    private fun setupCollections() {
        collectionAdapter = CollectionAdapter()
        binding.collectionRView.apply {
            layoutManager = GridLayoutManager(requireActivity(), 2)
            adapter = collectionAdapter
//            suppressLayout(true)
        }
    }

    /**
     * Setting up restaurant collection
     * Based on the api response observed, it can be sectioned list so that multiple sections can be managed.
     * However, Section RecyclerView is not required as nested RecyclerView can solve the problem with efficient way.
     * Under the adapter there is another recyclerview used for child item.
     */
    private fun setupRestaurantCollections() {
        restaurantListingAdapter = RestaurantsListingAdapter()

        binding.recommendedItemsView.apply {
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, true).apply {
                stackFromEnd = true
                // Recyclerview is wrap to height therefore scrolling is disabled and it will expand to the number of elements
                isEnabled = false
                // As there are nested recycler view therefore nested scrolling enabled.
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