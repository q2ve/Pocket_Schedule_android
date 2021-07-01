package com.q2ve.suai.ui.loginNavigation

/**
 * Created by Denis Shishkin on 16.04.2021.
 * qwq2eq@gmail.com
 */

interface LoginNavigationInterface {
	fun goToFirstScreen(isFromOnboarding: Boolean = false)
	fun goToSecondScreen(title: String)
	fun changeEllipseProperties(translationX: Float, translationY: Float, rotation: Float)
	fun goToCoreFragments()
}