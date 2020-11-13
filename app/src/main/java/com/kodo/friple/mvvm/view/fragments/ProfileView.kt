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
import kotlinx.android.synthetic.main.profile_screen.*

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

        setImages(binding.root)

        binding.viewModel = mProfileView
        return binding.root
    }

    private fun setImages(root: View) {
        val uri = mapOf(
            1 to Uri.parse("https://sun9-45.userapi.com/AziJn-jiwrW5ezv2SZ8hT-Cjg_X3QZoXPXJvbQ/D74bpUiIkgY.jpg"),
            2 to Uri.parse("https://sun9-2.userapi.com/b2e4EuX0MJexXQ6st0H9x1tsnvtnvA0mTdfp2w/9jqYnGV4kCg.jpg"),
            3 to Uri.parse("https://sun9-37.userapi.com/4XInHKPQ02wgvFEknbpEiK7dqUJ2x4LoK4oNMw/K2nhLD1041k.jpg"),
            4 to Uri.parse("https://sun9-15.userapi.com/qGhAPZNGJU26ksf1ky3sDH_lTSCY-xmxrsrsqw/z93Jzr0F1e8.jpg"),
            5 to Uri.parse("https://sun9-7.userapi.com/SiVNPKgxMdGNBfIO_9yGpNR0WowLHj4R6gJGmg/QDUbIWyjJco.jpg"),
            6 to Uri.parse("https://sun9-69.userapi.com/oIxVJDeMPPQwKed2z7Y5oO4xf_kwfRlAuSMQZQ/D9lTzRaspzI.jpg"),
            7 to Uri.parse("https://sun9-68.userapi.com/hUAB2m2kG4jqWJUBrUYetixIg5skkt7FL64EEA/rNrwHWDFqSk.jpg")
        )
        (root.findViewById<View>(R.id.iv_profile_photo) as SimpleDraweeView).setImageURI(uri[1], context)

        (root.findViewById<View>(R.id.simpleDraweeViewFriends) as SimpleDraweeView).setImageURI(uri[7], context)

        (root.findViewById<View>(R.id.simpleDraweeView) as SimpleDraweeView).setImageURI(uri[1], context)
        (root.findViewById<View>(R.id.simpleDraweeView2) as SimpleDraweeView).setImageURI(uri[2], context)
        (root.findViewById<View>(R.id.simpleDraweeView3) as SimpleDraweeView).setImageURI(uri[3], context)
        (root.findViewById<View>(R.id.simpleDraweeView5) as SimpleDraweeView).setImageURI(uri[4], context)
        (root.findViewById<View>(R.id.simpleDraweeView6) as SimpleDraweeView).setImageURI(uri[5], context)
        (root.findViewById<View>(R.id.simpleDraweeView7) as SimpleDraweeView).setImageURI(uri[6], context)
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