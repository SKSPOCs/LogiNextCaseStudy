package com.loginext.casestudy.ui

import android.os.Bundle
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.BuildCompat
import androidx.core.os.BuildCompat.PrereleaseSdkCheck
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.loginext.casestudy.R
import com.loginext.casestudy.databinding.ActivityMainBinding
import com.loginext.casestudy.ui.account.AccountFragment
import com.loginext.casestudy.ui.home.HomeFragment
import com.loginext.casestudy.ui.order.OrderFragment

@PrereleaseSdkCheck
class MainActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var currentFragment: Fragment
    private lateinit var fragmentTransaction: FragmentTransaction

    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Home"
        supportActionBar?.setIcon(R.drawable.ic_location)
        setupBottomNav()
        onBackPressHandle()


    }

    private fun onBackPressHandle() {
        if (BuildCompat.isAtLeastT()) {

            onBackInvokedDispatcher.registerOnBackInvokedCallback(OnBackInvokedDispatcher.PRIORITY_DEFAULT) {
                navigateToBack()
            }

        } else {
            onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    navigateToBack()
                }
            })

        }
    }


    private fun navigateToHome() {
        binding.bottomNavigationView.selectedItemId = R.id.bottom_menu_home
    }

    private fun navigateToBack() {

        if (binding.bottomNavigationView.selectedItemId != R.id.bottom_menu_home) {
            navigateToHome()
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }


    private fun setupBottomNav() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_menu_home -> {
                    showScreen(HomeFragment())
                }
                R.id.bottom_menu_order -> {
                    showScreen(OrderFragment())
                }
                R.id.bottom_menu_account -> {
                    showScreen(AccountFragment())
                }
            }
            true
        }
        navigateToHome()
    }


    private fun showScreen(fragment: Fragment) {
        currentFragment = fragment
        fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.fragmentContainerView.id, currentFragment).commit()
    }
}