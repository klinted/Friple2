package com.kodo.friple.mvvm.view.activities

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.kodo.friple.R
import com.kodo.friple.mvvm.view.fragments.ChatsView
import com.kodo.friple.mvvm.view.fragments.HomeView
import com.kodo.friple.mvvm.view.fragments.ProfileView
import com.ncapdevi.fragnav.FragNavController
import com.ncapdevi.fragnav.FragNavLogger
import com.ncapdevi.fragnav.FragNavSwitchController
import com.ncapdevi.fragnav.FragNavTransactionOptions
import com.ncapdevi.fragnav.tabhistory.UniqueTabHistoryStrategy
import kotlinx.android.synthetic.main.activity_main.*


const val INDEX_HOME = FragNavController.TAB1
const val INDEX_CHATS = FragNavController.TAB2
const val INDEX_PROFILE = FragNavController.TAB3

class MainActivity : AppCompatActivity(), FragNavController.RootFragmentListener,
    BottomNavigationBar.OnTabSelectedListener, FragNavController.TransactionListener{

    private val fragNavController: FragNavController = FragNavController(
        supportFragmentManager,
        R.id.fragment_container
    )

    override val numberOfRootFragments: Int = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBottomBar()

        fragNavController.apply {
            transactionListener = this@MainActivity
            rootFragmentListener = this@MainActivity
            createEager = true

            fragNavLogger = object : FragNavLogger {
                override fun error(message: String, throwable: Throwable) {
                    Log.e("MainActivity", message, throwable)
                }
            }

            fragmentHideStrategy = FragNavController.DETACH_ON_NAVIGATE_HIDE_ON_SWITCH

            navigationStrategy = UniqueTabHistoryStrategy(object : FragNavSwitchController {
                override fun switchTab(index: Int, transactionOptions: FragNavTransactionOptions?) {
                    bottom_navigation.selectTab(index)
                }
            })
        }

        fragNavController.initialize(INDEX_HOME, savedInstanceState)

        val initial = savedInstanceState == null
        if (initial) {
            bottom_navigation.selectTab(INDEX_HOME)
        }
    }

    private fun initBottomBar() {
        bottom_navigation.setTabSelectedListener(this)

        bottom_navigation
            .addItem(BottomNavigationItem(R.drawable.ic_home, "Home"))
            .addItem(BottomNavigationItem(R.drawable.ic_statistic, "Chats"))
            .addItem(BottomNavigationItem(R.drawable.ic_profile, "Profile"))
            .setFirstSelectedPosition(0)
            .setBarBackgroundColor(R.color.color_surface)
            .initialise()
    }

    override fun onTabSelected(position: Int) {
        when(position){
            0 -> fragNavController.switchTab(INDEX_HOME)
            1 -> fragNavController.switchTab(INDEX_CHATS)
            2 -> fragNavController.switchTab(INDEX_PROFILE)
        }
    }

    override fun onTabUnselected(position: Int) {
    }

    override fun onTabReselected(position: Int) {
        fragNavController.clearStack()
    }

    override fun getRootFragment(index: Int): Fragment {
        when(index){
            INDEX_HOME -> return HomeView()
            INDEX_CHATS -> return ChatsView()
            INDEX_PROFILE -> return ProfileView()
        }
        throw IllegalStateException("Index: $index")
    }

    override fun onFragmentTransaction(
        fragment: Fragment?,
        transactionType: FragNavController.TransactionType
    ) {
        supportActionBar?.setDisplayHomeAsUpEnabled(fragNavController.isRootFragment.not())
    }

    override fun onTabTransaction(fragment: Fragment?, index: Int) {
        supportActionBar?.setDisplayHomeAsUpEnabled(fragNavController.isRootFragment.not())
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        fragNavController.onSaveInstanceState(outState)

    }

    override fun onBackPressed() {
        if (fragNavController.popFragment().not()) {
            super.onBackPressed()
        }
    }
}

