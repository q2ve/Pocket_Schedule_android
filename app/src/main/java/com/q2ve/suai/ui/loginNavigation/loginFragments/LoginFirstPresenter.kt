package com.q2ve.suai.ui.loginNavigation.loginFragments

import android.app.Activity
import androidx.fragment.app.Fragment
import com.q2ve.suai.R
import com.q2ve.suai.interfacesRENAME.NavigationInterface
import com.q2ve.suai.ui.bottomMenu.BottomMenuPresenter
import com.q2ve.suai.ui.bottomMenu.BottomMenuView
import com.q2ve.suai.ui.loginNavigation.LoginNavigationInterface
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKScope

interface LoginScreenFirstPresenterInterface {
    fun enterButtonPressed ()
    fun vkButtonPressed (activity: Activity)
    fun backButtonPressed ()
    fun universitySelectorButtonPressed()
}

class LoginFirstPresenter(private val fragmentReplacer: NavigationInterface, private val parent: LoginNavigationInterface): LoginScreenFirstPresenterInterface, Fragment() {

    lateinit var view: LoginScreenFirstViewInterface

    override fun enterButtonPressed() {
        parent.goToSecondScreen()
    }

    override fun vkButtonPressed(activity: Activity) {
        VK.login(activity, arrayListOf(VKScope.WALL, VKScope.OFFLINE))
    }

    override fun backButtonPressed() {
        TODO("Not yet implemented")
    }

    override fun universitySelectorButtonPressed() {
        val bottomMenuView = BottomMenuView("Выбор хуйни", fragmentReplacer)
        val bottomMenuPresenter = BottomMenuPresenter(fragmentReplacer, bottomMenuView)
        bottomMenuView.presenter = bottomMenuPresenter
        fragmentReplacer.addFragment(R.id.login_navigation, bottomMenuView)
    }
}