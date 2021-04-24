package com.q2ve.suai

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.q2ve.suai.ui.RootNavigation
import com.q2ve.suai.ui.Screens.loginFirst
import com.q2ve.suai.ui.Screens.loginNavigation
import com.q2ve.suai.ui.Screens.rootNavigation
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback

interface NavigationInterface{
    fun navigateToLoginFirst(fragment: Fragment)
    fun navigateToLoginNavigation(fragment: Fragment)
    fun navigateToRootNavigation(fragment: Fragment)
    fun newRootScreenRootNavigation(fragment: Fragment)
}

class MainActivity : AppCompatActivity(), NavigationInterface {

    private val cicerone = Cicerone.create()
    private val mainRouter get() = cicerone.router
    private val mainNavigatorHolder get() = cicerone.getNavigatorHolder()
    private val mainNavigator = AppNavigator(this, R.id.main_activity_frame)

    private val rootNavigation = RootNavigation(this, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        newRootScreenRootNavigation(rootNavigation)
        rootNavigation.startFragmentSelect()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        mainNavigatorHolder.setNavigator(mainNavigator)
    }

    override fun onPause() {
        mainNavigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val callback = object: VKAuthCallback {
            override fun onLogin(token: VKAccessToken) {
                Log.d("Tag", token.accessToken)

            }

            override fun onLoginFailed(errorCode: Int) {
                // User didn't pass authorization
            }
        }
        if (data == null || !VK.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun navigateToLoginFirst(fragment: Fragment) {mainRouter.navigateTo(loginFirst(fragment))}
    override fun navigateToRootNavigation(fragment: Fragment) {mainRouter.navigateTo(rootNavigation(fragment))}
    override fun navigateToLoginNavigation(fragment: Fragment) {mainRouter.navigateTo(loginNavigation(fragment))}
    override fun newRootScreenRootNavigation(fragment: Fragment) {mainRouter.newRootScreen(rootNavigation(fragment))}
}