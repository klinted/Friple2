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
import com.kodo.friple.mvvm.common.MyViewModelFactory
import com.kodo.friple.mvvm.common.navigation.BackButtonListener
import com.kodo.friple.mvvm.common.navigation.RouterProvider
import com.kodo.friple.mvvm.viewmodel.HomeViewModel

class HomeView: Fragment(), BackButtonListener {

    lateinit var mHomeViewModel: HomeViewModel
    lateinit var binding: HomeScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_screen, container, false)

        // Pass router. We need them in viewModel
        val viewModelFactory = MyViewModelFactory((parentFragment as RouterProvider).router)
        mHomeViewModel = ViewModelProvider(this, viewModelFactory)
            .get(HomeViewModel::class.java)

        binding.viewModel = mHomeViewModel
        binding.executePendingBindings()

        return binding.root
    }

    override fun onBackPressed(): Boolean {
        mHomeViewModel.onBackPressed()
        return true
    }


    companion object {
        private const val EXTRA_NAME = "extra_name"
        private const val EXTRA_NUMBER = "extra_number"

        fun getNewInstance(name: String?, number: Int): HomeView {
            return HomeView().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_NAME, name)
                    putInt(EXTRA_NUMBER, number)
                }
            }
        }
    }

}