package com.q2ve.suai.ui.loginNavigation.loginFragments

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKScope

interface LoginScreenFirstPresenterInterface {
    fun viewCreated ()
    fun enterButtonPressed ()
    fun vkButtonPressed ()
    fun backButtonPressed ()
}

class LoginFirstPresenter: LoginScreenFirstPresenterInterface, Fragment() {

    override fun viewCreated() {
        TODO("Not yet implemented")
    }

    override fun enterButtonPressed() {
        TODO("Not yet implemented")
    }

    override fun vkButtonPressed() {
        VK.login(activity as AppCompatActivity, arrayListOf(VKScope.WALL, VKScope.OFFLINE))
    }

    override fun backButtonPressed() {
        TODO("Not yet implemented")
    }
}