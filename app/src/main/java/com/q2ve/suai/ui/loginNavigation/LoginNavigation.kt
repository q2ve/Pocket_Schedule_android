package com.q2ve.suai.ui.loginNavigation

/**
 * Created by Denis Shishkin on 16.04.2021.
 * qwq2eq@gmail.com
 */


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import com.q2ve.suai.R
import com.q2ve.suai.helpers.NavigationInterface
import com.q2ve.suai.helpers.ReplaceAnimation
import com.q2ve.suai.ui.loginNavigation.loginFragments.LoginFirstPresenter
import com.q2ve.suai.ui.loginNavigation.loginFragments.LoginFirstView
import com.q2ve.suai.ui.loginNavigation.loginFragments.LoginSecondPresenter
import com.q2ve.suai.ui.loginNavigation.loginFragments.LoginSecondView
import com.q2ve.suai.ui.loginNavigation.onboarding.OnboardingView

class LoginNavigation(private val fragmentReplacer: NavigationInterface): Fragment(), LoginNavigationInterface {

    private val loginFirstPresenter = LoginFirstPresenter(fragmentReplacer, this)
    private val loginFirstView = LoginFirstView()
    private val loginSecondPresenter = LoginSecondPresenter(fragmentReplacer, this)

    init {
        loginFirstView.presenter = loginFirstPresenter
        loginFirstPresenter.view = loginFirstView
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

            val backgroundEllipse: ImageView = view!!.findViewById(R.id.login_background_ellipse)
            //val ellipseRotation = backgroundEllipse.rotation+20
            val ellipseTranslationX = backgroundEllipse.translationX+600
            ViewCompat.animate(backgroundEllipse)
                //.rotation(ellipseRotation)
                .translationX(ellipseTranslationX)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .setDuration(320)
                .start()
        } else {
            fragmentReplacer.replaceFragment(R.id.login_navigation_frame, loginFirstView, ReplaceAnimation.LtR_slide)

            val backgroundEllipse: ImageView = view!!.findViewById(R.id.login_background_ellipse)
            val ellipseRotation = backgroundEllipse.rotation+10
            val ellipseTranslationX = backgroundEllipse.translationX+900
            ViewCompat.animate(backgroundEllipse)
                .rotation(ellipseRotation)
                .translationX(ellipseTranslationX)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .setDuration(320)
                .start()
        }
    }

    override fun goToSecondScreen(title: String) {
        val loginSecondView = LoginSecondView(loginSecondPresenter, title)
        fragmentReplacer.replaceFragment(R.id.login_navigation_frame, loginSecondView, ReplaceAnimation.RtL_slide)

        val backgroundEllipse: ImageView = view!!.findViewById(R.id.login_background_ellipse)
        val ellipseRotation = backgroundEllipse.rotation-10
        val ellipseTranslationX = backgroundEllipse.translationX-900
        ViewCompat.animate(backgroundEllipse)
            .rotation(ellipseRotation)
            .translationX(ellipseTranslationX)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .setDuration(320)
            .start()
    }

    override fun changeEllipseProperties(translationX: Float, translationY: Float, rotation: Float) {
        val backgroundEllipse: ImageView = view!!.findViewById(R.id.login_background_ellipse)
        backgroundEllipse.translationX = (translationX * resources.displayMetrics.density)
        backgroundEllipse.translationY = (translationY * resources.displayMetrics.density)
        backgroundEllipse.rotation = rotation
    }
}