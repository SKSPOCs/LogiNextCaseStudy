package com.loginext.casestudy.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.loginext.casestudy.databinding.OrderFragmentBinding

class OrderFragment : Fragment() {

    private var _binding: OrderFragmentBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        _binding = OrderFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

}