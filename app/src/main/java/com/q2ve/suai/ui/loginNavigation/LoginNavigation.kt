package com.q2ve.suai.ui.loginNavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.q2ve.suai.NavigationInterface
import com.q2ve.suai.R
import com.q2ve.suai.ui.loginNavigation.loginFragments.LoginFirstPresenter
import com.q2ve.suai.ui.loginNavigation.loginFragments.LoginFirstView

class LoginNavigation(private val navigationLink: NavigationInterface, private val activityLink: FragmentActivity): Fragment() {

    private val loginFirstPresenter = LoginFirstPresenter()
    private val loginFirstView = LoginFirstView()

    /*private val cicerone = Cicerone.create()
    private val loginRouter get() = cicerone.router
    private val loginNavigatorHolder get() = cicerone.getNavigatorHolder()
    private val loginNavigator = AppNavigator(activityLink, R.id.login_navigation_frame)*/

    init {
        loginFirstView.presenter=loginFirstPresenter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.login_navigation, container, false)



        return rootView
    }
}