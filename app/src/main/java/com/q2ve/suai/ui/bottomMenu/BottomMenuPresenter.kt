package com.q2ve.suai.ui.bottomMenu

/**
 * Created by Denis Shishkin on 16.04.2021.
 * qwq2eq@gmail.com
 */

import androidx.fragment.app.Fragment
import com.q2ve.suai.helpers.NavigationInterface

class BottomMenuPresenter(
	private val fragmentReplacer: NavigationInterface,
	private val view: Fragment,
	private val parent: BottomMenuInterface
	): BottomMenuPresenterInterface {

	override fun exitBottomMenu() {
		parent.bottomMenuClosed()
		fragmentReplacer.removeFragment(view)
	}
}