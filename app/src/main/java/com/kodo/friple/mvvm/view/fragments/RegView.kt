package com.kodo.friple.mvvm.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kodo.friple.R
import com.kodo.friple.databinding.RegScreenBinding
import com.kodo.friple.mvvm.viewmodel.RegLogViewModel

class RegView: Fragment() {

    lateinit var mLogViewModel: RegLogViewModel
    lateinit var binding: RegScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.reg_screen, container, false)
        mLogViewModel = ViewModelProvider(this).get(RegLogViewModel::class.java)
        binding.viewModel = mLogViewModel
        binding.executePendingBindings()

        return  binding.root
    }

}