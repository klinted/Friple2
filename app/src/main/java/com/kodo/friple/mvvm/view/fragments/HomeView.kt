package com.kodo.friple.mvvm.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kodo.friple.R
import com.kodo.friple.databinding.HomeScreenBinding
import com.kodo.friple.mvvm.viewmodel.HomeViewModel

class HomeView: Fragment() {

    lateinit var mHomeViewModel: HomeViewModel
    lateinit var binding: HomeScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_screen, container, false)
        mHomeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.viewModel = mHomeViewModel
        binding.executePendingBindings()

        return binding.root
    }
}