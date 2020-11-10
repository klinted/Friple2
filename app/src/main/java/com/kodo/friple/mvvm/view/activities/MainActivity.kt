package com.kodo.friple.mvvm.view.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.github.terrakok.cicerone.Router
import com.kodo.friple.R
import com.kodo.friple.databinding.ActivityMainBinding
import com.kodo.friple.mvvm.common.MyViewModelFactory
import com.kodo.friple.mvvm.common.SampleApplication
import com.kodo.friple.mvvm.common.Screens.Tab
import com.kodo.friple.mvvm.common.navigation.BackButtonListener
import com.kodo.friple.mvvm.common.navigation.RouterProvider
import com.kodo.friple.mvvm.viewmodel.BaseViewModel
import com.kodo.friple.mvvm.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), RouterProvider{

    lateinit var mActivityMainViewModel: MainActivityViewModel

    lateinit var binding: ActivityMainBinding

    @Inject
    override lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        SampleApplication.INSTANCE.appComponent.inject(this)

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val viewModelFactory = MyViewModelFactory(router)
        mActivityMainViewModel = ViewModelProvider(this, viewModelFactory)
            .get(MainActivityViewModel::class.java)

        binding.viewModel = mActivityMainViewModel

        initBottomBar()
        if (savedInstanceState == null) {
            bottom_navigation.selectTab(0, true)
        }
    }

    private fun initBottomBar() {

        bottom_navigation
            .addItem(BottomNavigationItem(R.drawable.ic_home, "Home"))
            .addItem(BottomNavigationItem(R.drawable.ic_statistic, "Chats"))
            .addItem(BottomNavigationItem(R.drawable.ic_profile, "Profile"))
            .setFirstSelectedPosition(0)
            .setBarBackgroundColor(R.color.color_surface)
            .initialise()

        bottom_navigation.setTabSelectedListener(object: BottomNavigationBar.OnTabSelectedListener{
            override fun onTabSelected(position: Int) {
                when (position) {
                    0 -> selectTab("HOME")
                    1 -> selectTab("CHATS")
                    2 -> selectTab("PROFILE")
                }
                bottom_navigation.selectTab(position, false)
            }

            override fun onTabUnselected(position: Int) {}

            override fun onTabReselected(position: Int) {
                onTabSelected(position)
            }
        })
    }

    private fun selectTab(tab: String){
        val fm = supportFragmentManager
        var currentFragment: Fragment? = null
        val fragments = fm.fragments

        Log.d("fff", "fragments: $fragments")

        for (f in fragments) {
            if (f.isVisible) {
                currentFragment = f
                break
            }
        }

        val newFragment = fm.findFragmentByTag(tab)

        Log.d("fff", "new fragment: $newFragment")

        if (currentFragment != null && newFragment != null && currentFragment === newFragment) return

        val transaction = fm.beginTransaction()

        if (newFragment == null) {
            transaction.add(
                R.id.fragment_container,
                Tab(tab).createFragment(fm.fragmentFactory), tab
            )
        }

        if (currentFragment != null) {
            transaction.hide(currentFragment)
        }

        if (newFragment != null) {
            transaction.show(newFragment)
        }

        transaction.commitNow()
    }

    override fun onBackPressed() {
        val fm = supportFragmentManager
        var fragment: Fragment? = null
        val fragments = fm.fragments
        for (f in fragments) {
            if (f.isVisible) {
                fragment = f
                break
            }
        }
        if (fragment != null && fragment is BackButtonListener
            && (fragment as BackButtonListener).onBackPressed()) {
            return
        } else {
            mActivityMainViewModel.onBackPressed()
        }
    }

}

