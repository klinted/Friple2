package com.kodo.friple.mvvm.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.kodo.friple.R

open class BaseView : Fragment() {

    internal var mInt = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        if (args != null) {
            mInt = args.getInt(ARGS_INSTANCE)
        }
    }

    companion object {
        const val ARGS_INSTANCE = "com.kodo.friple.argsInstance"
    }
}
