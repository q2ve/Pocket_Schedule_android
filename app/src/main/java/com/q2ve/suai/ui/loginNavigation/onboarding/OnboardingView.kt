package com.q2ve.suai.ui.loginNavigation.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.q2ve.suai.R
import com.q2ve.suai.ui.loginNavigation.LoginNavigationInterface

class OnboardingView(val parent: LoginNavigationInterface): Fragment() {

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val rootView = inflater.inflate(R.layout.login_onboarding, container, false)

		val adapter = OnboardingPagerAdapter(this, parent)
		val viewPager: ViewPager2 = rootView.findViewById(R.id.login_onboarding_pager)
		viewPager.adapter = adapter

		return rootView
	}
}