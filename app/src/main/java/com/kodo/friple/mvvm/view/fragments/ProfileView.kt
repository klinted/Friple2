package com.kodo.friple.mvvm.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kodo.friple.R
import com.kodo.friple.databinding.ProfileScreenBinding
import com.kodo.friple.mvvm.view.activities.MainActivity
import com.kodo.friple.mvvm.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.profile_screen.*

class ProfileView: Fragment() {

    lateinit var mProfileViewModel: ProfileViewModel
    lateinit var binding: ProfileScreenBinding

    override fun onCreateView(
        inflater : LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.profile_screen, container, false)
        mProfileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        binding.viewModel = mProfileViewModel
        binding.executePendingBindings()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {

        fun newInstance(instance: Int): ProfileView {
            val args = Bundle()
            args.putInt(BaseView.ARGS_INSTANCE, instance)
            val fragment = ProfileView()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("lc", "Resume")
    }

    override fun onStop() {
        super.onStop()
        Log.d("lc", "Stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("lc", "Destroy")
    }

}