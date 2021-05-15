package com.q2ve.suai.ui.bottomMenu

import androidx.fragment.app.Fragment
import com.q2ve.suai.interfacesRENAME.NavigationInterface
import com.q2ve.suai.ui.bottomMenu.selector.RecyclerInterface

interface BottomMenuPresenterInterface {
	fun exitButtonPressed()
}

class BottomMenuPresenter(private val fragmentReplacer: NavigationInterface, private val view: Fragment): BottomMenuPresenterInterface, RecyclerInterface {

	override fun exitButtonPressed() {
		fragmentReplacer.removeFragment(view)
	}

	override fun onRecyclerItemClicked(name: String) {
		TODO("Not yet implemented")
	}
}