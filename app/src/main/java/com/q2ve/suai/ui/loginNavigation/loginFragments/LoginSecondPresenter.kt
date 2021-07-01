package com.q2ve.suai.ui.loginNavigation.loginFragments

import com.q2ve.suai.ui.loginNavigation.LoginNavigationInterface

interface LoginScreenSecondPresenterInterface {
	fun viewCreated()
	fun enterButtonPressed(login: String, password: String)
	fun backButtonPressed()
}

class LoginSecondPresenter(private val parent: LoginNavigationInterface): LoginScreenSecondPresenterInterface {
	override fun viewCreated() {}

	override fun enterButtonPressed(login: String, password: String) {

	}

	override fun backButtonPressed() {
		parent.goToFirstScreen()
	}

}