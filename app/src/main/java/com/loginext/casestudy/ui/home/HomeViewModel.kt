package com.loginext.casestudy.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loginext.casestudy.data.Resource
import com.loginext.casestudy.data.models.Banner
import com.loginext.casestudy.data.models.FoodCategory
import com.loginext.casestudy.data.models.OfferCollection
import com.loginext.casestudy.data.models.RestaurantCollection
import com.loginext.casestudy.data.repository.DashboardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel() : ViewModel() {

    private val dashboardRepository = DashboardRepository()


    // Getting status of network request.
    private val _status = MutableLiveData<Resource<Any>>()
    val status: LiveData<Resource<Any>> = _status

    // Getting Banner data
    private val _banners = MutableLiveData<List<Banner>>()
    val banners: LiveData<List<Banner>> = _banners


    // Getting Food Category data
    private val _foodCategory = MutableLiveData<List<FoodCategory>>()
    val foodCategory: LiveData<List<FoodCategory>> = _foodCategory

    // Getting Voucher Info data
    private val _voucherCount = MutableLiveData<Int>()
    val voucherCount: LiveData<Int> = _voucherCount


    // Getting Collections data
    private val _collections = MutableLiveData<List<OfferCollection>>()
    val collections: LiveData<List<OfferCollection>> = _collections


    // Getting Restaurant Listings data
    private val _restaurantListing = MutableLiveData<List<RestaurantCollection>>()
    val restaurantListing: LiveData<List<RestaurantCollection>> = _restaurantListing


    init {
        getRestaurantData()
    }

    fun getRestaurantData() = viewModelScope.launch { getDashboard() }


    private suspend fun getDashboard() {
        _status.postValue(Resource.Loading())
        try {
            val response = dashboardRepository.getDashboardData()

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        val dashboardData = it.data
                        dashboardData?.let { dashData ->
                            _status.postValue(Resource.Success(dashData))
                            _banners.postValue(dashData.banners)
                            _voucherCount.postValue((dashData.activeVouchers))
                            _collections.postValue(dashData.offerCollection)
                            _foodCategory.postValue(dashData.foodCategories)
                            _restaurantListing.postValue(dashData.restaurantCollections)
                        } ?: run { postDataError() }

                    } ?: run { postDataError() }


                } else {
                    postDataError()
                }
            }

        } catch (e: java.lang.Exception) {
            _status.postValue(Resource.Error(null, "Network Connection Error"))
        }
    }

    private fun postDataError() {
        _status.postValue(Resource.Error(null, "Unexpected Response"))
    }


}