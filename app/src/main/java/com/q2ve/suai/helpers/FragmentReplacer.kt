package com.q2ve.suai.helpers

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.q2ve.suai.R

enum class ReplaceAnimation {
	None, LtR_slide, RtL_slide
}

object FragmentReplacer: Fragment() {

	lateinit var activityLink: FragmentActivity

	fun replaceFragment(fragmentContainer: Int, fragment: Fragment, animation: ReplaceAnimation = ReplaceAnimation.None) {
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

	fun addFragment(fragmentContainer: Int, fragment: Fragment) {
		val fragmentManager = activityLink.supportFragmentManager
		val transaction = fragmentManager.beginTransaction()
		transaction.add(fragmentContainer, fragment)
		transaction.addToBackStack(null)
		transaction.commit()
	}
	fun removeFragment(fragment: Fragment) {
		val fragmentManager = activityLink.supportFragmentManager
		val transaction = fragmentManager.beginTransaction()
		transaction.remove(fragment)
		transaction.addToBackStack(null)
		transaction.commit()
	}
}