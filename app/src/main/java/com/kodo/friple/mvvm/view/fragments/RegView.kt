package com.kodo.friple.mvvm.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import com.kodo.friple.R
import com.kodo.friple.databinding.RegScreenBinding
import com.kodo.friple.mvvm.viewmodel.RegViewModel

class RegView: Fragment() {

    lateinit var binding: RegScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.reg_screen, container, false)
        val viewModel = ViewModelProvider(this).get(RegViewModel::class.java)
        binding.viewModel = viewModel
        binding.executePendingBindings()

        return  binding.root
    }

}