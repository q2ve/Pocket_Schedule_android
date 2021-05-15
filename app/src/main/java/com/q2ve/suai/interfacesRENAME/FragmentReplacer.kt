package com.q2ve.suai.interfacesRENAME

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

interface NavigationInterface {
	fun replaceFragment (fragmentContainer: Int, fragment: Fragment)
	fun addFragment (fragmentContainer: Int, fragment: Fragment)
	fun removeFragment (fragment: Fragment)
}

class FragmentReplacer(private val activityLink: FragmentActivity): Fragment(), NavigationInterface {
	override fun replaceFragment(fragmentContainer: Int, fragment: Fragment) {
		val fragmentManager = activityLink.supportFragmentManager
		val transaction = fragmentManager.beginTransaction()
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