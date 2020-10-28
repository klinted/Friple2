package com.kodo.friple.mvvm.view.activities

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.github.terrakok.cicerone.Cicerone
import com.kodo.friple.R


class MainActivity : AppCompatActivity() {

    private val cicerone = Cicerone.create()
    val globalRouter get() = cicerone.router
    val navigationHolder get() = cicerone.getNavigatorHolder()

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Change navigation bar color
        window.navigationBarColor = ContextCompat.getColor(this, R.color.color_background)

        INSTANCE = this
    }

    companion object {
        internal lateinit var INSTANCE: MainActivity
            private set
    }

}