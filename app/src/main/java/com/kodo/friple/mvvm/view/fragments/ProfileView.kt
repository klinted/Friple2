package com.kodo.friple.mvvm.view.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.facebook.drawee.view.SimpleDraweeView
import com.kodo.friple.R
import com.kodo.friple.databinding.ProfileScreenBinding
import com.kodo.friple.mvvm.common.MyViewModelFactory
import com.kodo.friple.mvvm.common.navigation.BackButtonListener
import com.kodo.friple.mvvm.common.navigation.RouterProvider
import com.kodo.friple.mvvm.viewmodel.ProfileViewModel

class ProfileView: Fragment(), BackButtonListener{

    lateinit var mProfileView: ProfileViewModel
    lateinit var binding: ProfileScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.profile_screen, container, false)

        val viewModelFactory = MyViewModelFactory(
            (parentFragment as RouterProvider).router,
            context!!
        )
        mProfileView = ViewModelProvider(this, viewModelFactory)
            .get(ProfileViewModel::class.java)

        val uri = Uri.parse(
            "https://sun9-45.userapi.com/AziJn-jiwrW5ezv2SZ8hT-Cjg_X3QZoXPXJvbQ/D74bpUiIkgY.jpg"
        )
        val draweeView = binding.root.findViewById<View>(R.id.my_image_view) as SimpleDraweeView
        draweeView.setImageURI(uri)

        binding.viewModel = mProfileView
        return binding.root
    }

    override fun onBackPressed(): Boolean {
        mProfileView.onBackPressed()
        return true
    }

    companion object {
        private const val EXTRA_NAME = "extra_name"
        private const val EXTRA_NUMBER = "extra_number"

        fun getNewInstance(name: String?, number: Int): ProfileView {
            return ProfileView().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_NAME, name)
                    putInt(EXTRA_NUMBER, number)
                }
            }
        }
    }
}