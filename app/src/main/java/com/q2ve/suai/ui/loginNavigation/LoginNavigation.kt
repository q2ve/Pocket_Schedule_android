package com.q2ve.suai.ui.loginNavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import com.q2ve.suai.R
import com.q2ve.suai.interfacesRENAME.NavigationInterface
import com.q2ve.suai.interfacesRENAME.ReplaceAnimation
import com.q2ve.suai.ui.loginNavigation.loginFragments.LoginFirstPresenter
import com.q2ve.suai.ui.loginNavigation.loginFragments.LoginFirstView
import com.q2ve.suai.ui.loginNavigation.loginFragments.LoginSecondPresenter
import com.q2ve.suai.ui.loginNavigation.loginFragments.LoginSecondView
import com.q2ve.suai.ui.loginNavigation.onboarding.OnboardingView

interface LoginNavigationInterface {
    fun goToFirstScreen(isFromOnboarding: Boolean = false)
    fun goToSecondScreen(title: String)
}

class LoginNavigation(private val fragmentReplacer: NavigationInterface): Fragment(), LoginNavigationInterface {

    private val loginFirstPresenter = LoginFirstPresenter(fragmentReplacer, this)
    private val loginFirstView = LoginFirstView()
    private val loginSecondPresenter = LoginSecondPresenter(fragmentReplacer, this)

    init {
        loginFirstView.presenter=loginFirstPresenter
        loginFirstPresenter.view=loginFirstView
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.login_navigation, container, false)

        //fragmentReplacer.replaceFragment(R.id.login_navigation_frame, loginFirstView)
        fragmentReplacer.replaceFragment(R.id.login_navigation_frame, OnboardingView(this))

        return rootView
    }

    override fun goToFirstScreen(isFromOnboarding: Boolean) {
        if (isFromOnboarding) {
            fragmentReplacer.replaceFragment(R.id.login_navigation_frame, loginFirstView, ReplaceAnimation.RtL_slide)
        } else {
            fragmentReplacer.replaceFragment(R.id.login_navigation_frame, loginFirstView, ReplaceAnimation.LtR_slide)

            val backgroundEllipse: ImageView = view!!.findViewById(R.id.login_background_ellipse)
            val ellipseRotation = backgroundEllipse.rotation+40
            ViewCompat.animate(backgroundEllipse)
                .rotation(ellipseRotation)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .setDuration(600)
                .start()
        }
    }

    override fun goToSecondScreen(title: String) {
        val loginSecondView = LoginSecondView(loginSecondPresenter, title)
        fragmentReplacer.replaceFragment(R.id.login_navigation_frame, loginSecondView, ReplaceAnimation.RtL_slide)

        val backgroundEllipse: ImageView = view!!.findViewById(R.id.login_background_ellipse)
        val ellipseRotation = backgroundEllipse.rotation-40
        ViewCompat.animate(backgroundEllipse)
            .rotation(ellipseRotation)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .setDuration(600)
            .start()
    }
}