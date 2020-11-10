package com.kodo.friple.mvvm.view.containers

import android.app.Activity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Forward
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.kodo.friple.R
import com.kodo.friple.apputil.AppConfig
import com.kodo.friple.mvvm.common.SampleApplication
import com.kodo.friple.mvvm.common.Screens.Chats
import com.kodo.friple.mvvm.common.Screens.Home
import com.kodo.friple.mvvm.common.Screens.LogProfile
import com.kodo.friple.mvvm.common.Screens.Profile
import com.kodo.friple.mvvm.common.navigation.BackButtonListener
import com.kodo.friple.mvvm.common.navigation.LocalCiceroneHolder
import com.kodo.friple.mvvm.common.navigation.RouterProvider
import javax.inject.Inject

class TabContainerFragment : Fragment(), RouterProvider, BackButtonListener {

    lateinit var mAppConfig: AppConfig

    private val navigator: Navigator by lazy {
        AppNavigator(
            activity!!,
            R.id.ftc_container,
            childFragmentManager,
            childFragmentManager.fragmentFactory
        )
    }

    @Inject
    lateinit var ciceroneHolder: LocalCiceroneHolder

    private val containerName: String
        get() = arguments?.getString(EXTRA_NAME)!!

    override fun onCreate(savedInstanceState: Bundle?) {
        SampleApplication.INSTANCE.appComponent.inject(this)
        super.onCreate(savedInstanceState)

        mAppConfig = AppConfig(context!!)
    }

    private val cicerone: Cicerone<Router>
        get() = ciceroneHolder.getCicerone(containerName)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab_container, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (childFragmentManager.findFragmentById(R.id.ftc_container) == null) {
            when(containerName){
                "HOME" -> cicerone.router.replaceScreen(Home(containerName, 0))
                "CHATS" -> cicerone.router.replaceScreen(Chats(containerName, 0))
                "PROFILE" -> {
                    if (mAppConfig.isUserLogin()) {
                        cicerone.router.replaceScreen(Profile(containerName, 0))
                    } else {
                        cicerone.router.replaceScreen(LogProfile(containerName, 0))
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        cicerone.getNavigatorHolder().setNavigator(navigator)
    }

    override fun onPause() {
        cicerone.getNavigatorHolder().removeNavigator()
        super.onPause()
    }

    override val router: Router
        get() = cicerone.router

    override fun onBackPressed(): Boolean {
        val fragment = childFragmentManager.findFragmentById(R.id.ftc_container)
        return if (fragment != null && fragment is BackButtonListener
            && (fragment as BackButtonListener).onBackPressed()
        ) {
            true
        } else {
            (activity as RouterProvider?)!!.router.exit()
            true
        }
    }

    companion object {
        private const val EXTRA_NAME = "tcf_extra_name"

        fun getNewInstance(name: String?) =
            TabContainerFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_NAME, name)
                }
            }
    }
}