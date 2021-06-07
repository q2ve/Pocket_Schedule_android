package com.q2ve.suai.ui.loginNavigation.loginFragments

import android.app.Activity
import androidx.fragment.app.Fragment
import com.q2ve.suai.R
import com.q2ve.suai.interfacesRENAME.NavigationInterface
import com.q2ve.suai.ui.bottomMenu.BottomMenuPresenter
import com.q2ve.suai.ui.bottomMenu.BottomMenuView
import com.q2ve.suai.ui.bottomMenu.selector.BottomMenuInterface
import com.q2ve.suai.ui.loginNavigation.LoginNavigationInterface
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKScope

interface LoginScreenFirstPresenterInterface {
    fun enterButtonPressed (universityName: String)
    fun vkButtonPressed (activity: Activity)
    fun backButtonPressed ()
    fun universitySelectorButtonPressed()
}

class LoginFirstPresenter(private val fragmentReplacer: NavigationInterface, private val parent: LoginNavigationInterface): LoginScreenFirstPresenterInterface, BottomMenuInterface, Fragment() {

    lateinit var view: LoginScreenFirstViewInterface

    override fun enterButtonPressed(universityName: String) {
        view.makeErrorMessage(true, 1)
        if (universityName.length<2) {
            view.makeErrorMessage(false, R.string.error_no_university_selected)
        }
        else {
            parent.goToSecondScreen(universityName)
        }
    }

    override fun vkButtonPressed(activity: Activity) {
        VK.login(activity, arrayListOf(VKScope.WALL, VKScope.OFFLINE))
    }

    override fun backButtonPressed() {
        TODO("Not yet implemented")
    }

    override fun universitySelectorButtonPressed() {
        val bottomMenuView = BottomMenuView("Выбор хуйни", fragmentReplacer)
        val bottomMenuPresenter = BottomMenuPresenter(fragmentReplacer, bottomMenuView, this)
        bottomMenuView.presenter = bottomMenuPresenter
        fragmentReplacer.addFragment(R.id.login_navigation, bottomMenuView)
    }

    override fun onBottomMenuResponse(item: String) {
        view.makeErrorMessage(true, 1)
        view.bindNewUniversityName(item)
    }
}