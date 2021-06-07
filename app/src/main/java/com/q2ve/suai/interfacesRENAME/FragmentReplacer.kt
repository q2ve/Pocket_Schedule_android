package com.q2ve.suai.interfacesRENAME

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.q2ve.suai.R

interface NavigationInterface {
	fun replaceFragment (fragmentContainer: Int, fragment: Fragment, animation: ReplaceAnimation = ReplaceAnimation.None)
	fun addFragment (fragmentContainer: Int, fragment: Fragment)
	fun removeFragment (fragment: Fragment)
}

enum class ReplaceAnimation {
	None, LtR_slide, RtL_slide
}

class FragmentReplacer(private val activityLink: FragmentActivity): Fragment(), NavigationInterface {

	override fun replaceFragment(fragmentContainer: Int, fragment: Fragment, animation: ReplaceAnimation) {
		val fragmentManager = activityLink.supportFragmentManager
		val transaction = fragmentManager.beginTransaction()
		when (animation) {
			ReplaceAnimation.LtR_slide -> {
				transaction.setCustomAnimations(R.animator.slide_in_right_enter, R.animator.slide_in_right_exit)
			}
			ReplaceAnimation.RtL_slide -> {
				transaction.setCustomAnimations(R.animator.slide_in_left_enter, R.animator.slide_in_left_exit)
			}
			ReplaceAnimation.None -> {
				//without animation
			}
		}
		transaction.replace(fragmentContainer, fragment)
		transaction.addToBackStack(null)
		transaction.commit()
	}

	override fun addFragment(fragmentContainer: Int, fragment: Fragment) {
		val fragmentManager = activityLink.supportFragmentManager
		val transaction = fragmentManager.beginTransaction()
		transaction.add(fragmentContainer, fragment)
		transaction.addToBackStack(null)
		transaction.commit()
	}
	override fun removeFragment(fragment: Fragment) {
		val fragmentManager = activityLink.supportFragmentManager
		val transaction = fragmentManager.beginTransaction()
		transaction.remove(fragment)
		transaction.addToBackStack(null)
		transaction.commit()
	}
}