package com.q2ve.suai.ui.bottomMenu

import androidx.fragment.app.Fragment
import com.q2ve.suai.interfacesRENAME.NavigationInterface
import com.q2ve.suai.ui.bottomMenu.selector.BottomMenuInterface

interface BottomMenuPresenterInterface {
	fun exitButtonPressed()
	fun onRecyclerItemClicked(name: String)
}

class BottomMenuPresenter(private val fragmentReplacer: NavigationInterface, private val view: Fragment, private val parent: BottomMenuInterface): BottomMenuPresenterInterface {

	override fun exitButtonPressed() {
		fragmentReplacer.removeFragment(view)
	}

	override fun onRecyclerItemClicked(name: String) {
		parent.onBottomMenuResponse(name)
	}
}