package com.kodo.friple.mvvm.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.kodo.friple.R
import com.kodo.friple.databinding.LogScreenBinding
import com.kodo.friple.mvvm.viewmodel.RegLogViewModel

class LogView: Fragment() {

    lateinit var mRegLogViewModel: RegLogViewModel
    lateinit var binding: LogScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.log_screen, container, false)
        mRegLogViewModel = ViewModelProvider(this).get(RegLogViewModel::class.java)
        binding.viewModel = mRegLogViewModel
        binding.executePendingBindings()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mRegLogViewModel.snackBarStatus.observe(viewLifecycleOwner,  { status ->
            status?.let {
                mRegLogViewModel.snackBarStatus.value = null
                Snackbar.make(view,
                    "${mRegLogViewModel.message.value}",
                    Snackbar.LENGTH_LONG).show()
            }
        })
    }
}