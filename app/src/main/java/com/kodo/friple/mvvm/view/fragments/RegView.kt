package com.kodo.friple.mvvm.view.fragments

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.kodo.friple.R
import com.kodo.friple.apputil.AppConfig
import com.kodo.friple.databinding.RegScreenBinding
import com.kodo.friple.mvvm.common.MyViewModelFactory
import com.kodo.friple.mvvm.common.navigation.BackButtonListener
import com.kodo.friple.mvvm.common.navigation.RouterProvider
import com.kodo.friple.mvvm.viewmodel.RegLogViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.log_screen.*
import kotlinx.android.synthetic.main.reg_screen.*

class RegView: Fragment(), BackButtonListener, TextWatcher{

    lateinit var mRegLogViewModel: RegLogViewModel
    lateinit var binding: RegScreenBinding

    lateinit var mAppConfig: AppConfig

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.reg_screen, container, false)

        // Pass context and router. We need them in viewModel
        val viewModelFactory = MyViewModelFactory((parentFragment as RouterProvider).router, context!!)
        mRegLogViewModel = ViewModelProvider(this, viewModelFactory)
            .get(RegLogViewModel::class.java)

        binding.viewModel = mRegLogViewModel
        binding.executePendingBindings()

        // For change check loginned state
        mAppConfig = AppConfig(context!!)

        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Bottom navigation we don't need in this fragment
        activity?.bottom_navigation?.hide()
        btn_sign_up.isClickable = false

        // Add textListeners for change clickable, color Reg button and check for empty string
        et_reg_login.addTextChangedListener(this)
        et_reg_email.addTextChangedListener(this)
        et_reg_password.addTextChangedListener(this)

        // When we get response from server, snackBarStatus sets true and we do this method
        mRegLogViewModel.snackBarStatus.observe(viewLifecycleOwner, { status ->
            status?.let {
                mRegLogViewModel.snackBarStatus.value = null
                mRegLogViewModel.animate.value = null

                val handler = Handler()

                // If user registration is done successfully (we check it in appConfig where exists
                // string "isUserLogin"). Then starts doneLoadingAnimation with successful icon in finish,
                // and move to Profile Screen
                // Else starts doneLoadingAnimation with unsuccessful icon in finish,
                // and calls revertAnimation method
                if (mAppConfig.isUserLogin()) {
                    btn_sign_up.doneLoadingAnimation(
                        ContextCompat.getColor(context!!, R.color.color_secondary),
                        Bitmap.createBitmap(
                            BitmapFactory.decodeResource(resources, R.drawable.ic_done_white_48dp)
                        )
                    )

                    // Wait, cause we need time for anim finishing
                    handler.postDelayed({
                        mRegLogViewModel.openProfile()
                    }, 2000)
                } else {
                    btn_sign_up.doneLoadingAnimation(
                        ContextCompat.getColor(context!!, R.color.color_primary),
                        Bitmap.createBitmap(
                            BitmapFactory.decodeResource(resources, R.drawable.ic_error)
                        )
                    )

                    // Wait, cause we need time for anim finishing
                    handler.postDelayed({
                        btn_sign_up?.revertAnimation {
                            // Set last background (now button is round)
                            btn_sign_in?.setBackgroundResource(R.drawable.active_button)
                        }
                    }, 2000)
                }

                // Shows snackBar with message
                Snackbar.make(view,
                    "${mRegLogViewModel.message.value}",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        })

        // Here starts button's animation
        mRegLogViewModel.animate.observe(viewLifecycleOwner, { status ->
            status?.let {
                btn_sign_up.startAnimation()
                // Cause button will be round. If we don't do this, in last button's state, background
                // will be square. That's not good
                btn_sign_up.setBackgroundResource(R.drawable.circle_shape)
            }
        })
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // Here we check empty string
        if (et_reg_login.text.toString().replace(" ", "").isNotEmpty() &&
            et_reg_email.text.toString().replace(" ", "").isNotEmpty() &&
            et_reg_password.text.toString().replace(" ", "").isNotEmpty()){

            // If editText is not null change button's background to active color by dataBinding
            // and sets clickable status true
            mRegLogViewModel.isSelectedBackground.set(true)
            btn_sign_up.isClickable = true
        } else {
            // If editText is null change button's background to inactive color by dataBinding
            // and sets clickable status false
            mRegLogViewModel.isSelectedBackground.set(false)
            btn_sign_up.isClickable = false
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun afterTextChanged(s: Editable?) {}

    override fun onBackPressed(): Boolean {
        mRegLogViewModel.onBackPressed()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        // Here shows bottom navigation, cause user need it in Profile screen
        activity?.bottom_navigation?.show()
    }

    companion object {
        private const val EXTRA_NAME = "extra_name"
        private const val EXTRA_NUMBER = "extra_number"

        fun getNewInstance(name: String?, number: Int): RegView {
            return RegView().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_NAME, name)
                    putInt(EXTRA_NUMBER, number)
                }
            }
        }
    }

}