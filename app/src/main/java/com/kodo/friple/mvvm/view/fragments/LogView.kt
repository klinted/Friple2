package com.kodo.friple.mvvm.view.fragments

import android.graphics.*
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.kodo.friple.R
import com.kodo.friple.apputil.AppConfig
import com.kodo.friple.databinding.LogScreenBinding
import com.kodo.friple.mvvm.common.MyViewModelFactory
import com.kodo.friple.mvvm.common.navigation.BackButtonListener
import com.kodo.friple.mvvm.common.navigation.RouterProvider
import com.kodo.friple.mvvm.viewmodel.RegLogViewModel
import kotlinx.android.synthetic.main.log_screen.*


class LogView : Fragment(), BackButtonListener, TextWatcher {

    lateinit var mRegLogViewModel: RegLogViewModel
    lateinit var binding: LogScreenBinding

    lateinit var mAppConfig: AppConfig

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.log_screen, container, false)

        val viewModelFactory =
            MyViewModelFactory((parentFragment as RouterProvider).router, context!!)
        mRegLogViewModel = ViewModelProvider(this, viewModelFactory)
            .get(RegLogViewModel::class.java)

        binding.viewModel = mRegLogViewModel
        binding.executePendingBindings()

        mAppConfig = AppConfig(context!!)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        et_login.addTextChangedListener(this)
        et_password.addTextChangedListener(this)

        mRegLogViewModel.snackBarStatus.observe(viewLifecycleOwner, { status ->
            status?.let {
                mRegLogViewModel.snackBarStatus.value = null
                mRegLogViewModel.animate.value = null

                val handler = Handler()

                if (mAppConfig.isUserLogin()) {
                    btn_sign_in.doneLoadingAnimation(
                        ContextCompat.getColor(context!!, R.color.color_secondary),
                        Bitmap.createBitmap(
                            BitmapFactory.decodeResource(resources, R.drawable.ic_done_white_48dp)
                        )
                    )

                    handler.postDelayed({
                        mRegLogViewModel.openProfile()
                    }, 1000)
                } else {
                    btn_sign_in.doneLoadingAnimation(
                        ContextCompat.getColor(context!!, R.color.color_primary),
                        Bitmap.createBitmap(
                            BitmapFactory.decodeResource(resources, R.drawable.ic_x)
                        )
                    )

                    handler.postDelayed({
                        btn_sign_in.revertAnimation {
                            btn_sign_in.setBackgroundResource(R.drawable.active_button)
                        }
                    }, 2000)
                }

                Snackbar.make(
                    view,
                    "${mRegLogViewModel.message.value}",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        })

        mRegLogViewModel.animate.observe(viewLifecycleOwner, { status ->
            status?.let {
                btn_sign_in.startAnimation()
                btn_sign_in.setBackgroundResource(R.drawable.circle_shape)
            }
        })
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        if (et_login.text.toString().replace(" ", "").isNotEmpty() &&
            et_password.text.toString().replace(" ", "").isNotEmpty()) {

            mRegLogViewModel.isSelectedBackground.set(true)
            btn_sign_in.isClickable = true
        } else {
            mRegLogViewModel.isSelectedBackground.set(false)
            btn_sign_in.isClickable = false
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun afterTextChanged(s: Editable?) {}

    override fun onBackPressed(): Boolean {
        mRegLogViewModel.onBackPressed()
        return true
    }

    companion object {
        private const val EXTRA_NAME = "extra_name"
        private const val EXTRA_NUMBER = "extra_number"

        fun getNewInstance(name: String?, number: Int): LogView {
            return LogView().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_NAME, name)
                    putInt(EXTRA_NUMBER, number)
                }
            }
        }
    }

}