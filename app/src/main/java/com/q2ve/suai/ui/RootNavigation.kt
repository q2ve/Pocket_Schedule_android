package com.q2ve.suai.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.q2ve.suai.NavigationInterface
import com.q2ve.suai.R
import com.q2ve.suai.ui.Screens.loginNavigation
import com.q2ve.suai.ui.coreNavigation.CoreNavigation
import com.q2ve.suai.ui.loginNavigation.LoginNavigation

class RootNavigation(private val navigationLink: NavigationInterface, private val activityLink: FragmentActivity): Fragment() {

    /*init {
        navigationLink.newRootScreenRootNavigation(this)
    }*/

    private val cicerone = Cicerone.create()
    private val rootRouter get() = cicerone.router
    private val rootNavigatorHolder get() = cicerone.getNavigatorHolder()
    private val rootNavigator = AppNavigator(activityLink, R.id.login_navigation_frame)

    private val loginNavigation = LoginNavigation(navigationLink, activityLink)
    private val coreNavigation =  CoreNavigation()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.root_navigation, container, false)

        Log.d("TAGGGGGGGGGGGGGGGGGGGGG", "onCreateView")

        //rootRouter.newRootScreen(loginNavigation(loginNavigation))

        return rootView
    }

    override fun onResume() {
        super.onResume()
        //rootNavigatorHolder.setNavigator(rootNavigator)
        Log.d("TAGGGGGGGGGGGGGGGGGGGGG", "onResume")
    }

    override fun onPause() {
        //rootNavigatorHolder.removeNavigator()
        super.onPause()
        Log.d("TAGGGGGGGGGGGGGGGGGGGGG", "onPause")
    }

    fun startFragmentSelect () {rootRouter.newRootScreen(loginNavigation(loginNavigation))}
}