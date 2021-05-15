package com.q2ve.suai.ui.loginNavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.q2ve.suai.R
import com.q2ve.suai.interfacesRENAME.NavigationInterface
import com.q2ve.suai.ui.loginNavigation.loginFragments.LoginFirstPresenter
import com.q2ve.suai.ui.loginNavigation.loginFragments.LoginFirstView
import com.q2ve.suai.ui.loginNavigation.loginFragments.LoginSecondPresenter
import com.q2ve.suai.ui.loginNavigation.loginFragments.LoginSecondView

interface LoginNavigationInterface {
    fun goToSecondScreen()
}

class LoginNavigation(private val fragmentReplacer: NavigationInterface): Fragment(), LoginNavigationInterface {

    private val loginFirstPresenter = LoginFirstPresenter(fragmentReplacer, this)
    private val loginFirstView = LoginFirstView()
    private val loginSecondPresenter = LoginSecondPresenter(fragmentReplacer, this)
    private val loginSecondView = LoginSecondView()

    init {
        loginFirstView.presenter=loginFirstPresenter
        loginFirstPresenter.view=loginFirstView
        loginSecondView.presenter=loginSecondPresenter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.login_navigation, container, false)

        fragmentReplacer.replaceFragment(R.id.login_navigation_frame, loginFirstView)

        return rootView
    }

    override fun goToSecondScreen() {
        fragmentReplacer.replaceFragment(R.id.login_navigation_frame, loginSecondView)
    }
}