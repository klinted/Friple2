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

        val viewModelFactory = MyViewModelFactory((parentFragment as RouterProvider).router, context!!)
        mRegLogViewModel = ViewModelProvider(this, viewModelFactory)
            .get(RegLogViewModel::class.java)

        binding.viewModel = mRegLogViewModel
        binding.executePendingBindings()

        mAppConfig = AppConfig(context!!)

        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.bottom_navigation?.hide()
        btn_sign_up.isClickable = false

        et_reg_login.addTextChangedListener(this)
        et_reg_email.addTextChangedListener(this)
        et_reg_password.addTextChangedListener(this)

        mRegLogViewModel.snackBarStatus.observe(viewLifecycleOwner, { status ->
            status?.let {
                mRegLogViewModel.snackBarStatus.value = null
                mRegLogViewModel.animate.value = null

                val handler = Handler()

                if (mAppConfig.isUserLogin()) {
                    btn_sign_up.doneLoadingAnimation(
                        ContextCompat.getColor(context!!, R.color.color_secondary),
                        Bitmap.createBitmap(
                            BitmapFactory.decodeResource(resources, R.drawable.ic_done_white_48dp)
                        )
                    )

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

                    handler.postDelayed({
                        btn_sign_up?.revertAnimation {
                            btn_sign_in?.setBackgroundResource(R.drawable.active_button)
                        }
                    }, 2000)
                }

                Snackbar.make(view,
                    "${mRegLogViewModel.message.value}",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        })

        mRegLogViewModel.animate.observe(viewLifecycleOwner, { status ->
            status?.let {
                btn_sign_up.startAnimation()
                btn_sign_up.setBackgroundResource(R.drawable.circle_shape)
            }
        })
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if (et_reg_login.text.toString().replace(" ", "").isNotEmpty() &&
            et_reg_email.text.toString().replace(" ", "").isNotEmpty() &&
            et_reg_password.text.toString().replace(" ", "").isNotEmpty()){

            mRegLogViewModel.isSelectedBackground.set(true)
            btn_sign_up.isClickable = true
        } else {
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