package com.loginext.casestudy.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.loginext.casestudy.databinding.AccountFragmentBinding

class AccountFragment : Fragment() {

    private var _binding: AccountFragmentBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        _binding = AccountFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

}