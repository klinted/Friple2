package com.kodo.friple.mvvm.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kodo.friple.R
import com.kodo.friple.databinding.LogProfileScreenBinding
import com.kodo.friple.databinding.ProfileScreenBinding
import com.kodo.friple.mvvm.common.MyViewModelFactory
import com.kodo.friple.mvvm.common.navigation.BackButtonListener
import com.kodo.friple.mvvm.common.navigation.RouterProvider
import com.kodo.friple.mvvm.viewmodel.LogProfileViewModel

class LogProfileView: Fragment(), BackButtonListener {

    lateinit var mLogProfileViewModel: LogProfileViewModel
    lateinit var binding: LogProfileScreenBinding

    override fun onCreateView(
        inflater : LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.log_profile_screen, container, false)

        val viewModelFactory = MyViewModelFactory((parentFragment as RouterProvider).router)
        mLogProfileViewModel = ViewModelProvider(this, viewModelFactory)
            .get(LogProfileViewModel::class.java)


        binding.viewModel = mLogProfileViewModel
        binding.executePendingBindings()

        return binding.root
    }

    override fun onBackPressed(): Boolean {
        mLogProfileViewModel.onBackPressed()
        return true
    }

    companion object {
        private const val EXTRA_NAME = "extra_name"
        private const val EXTRA_NUMBER = "extra_number"

        fun getNewInstance(name: String?, number: Int): LogProfileView {
            return LogProfileView().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_NAME, name)
                    putInt(EXTRA_NUMBER, number)
                }
            }
        }
    }
}