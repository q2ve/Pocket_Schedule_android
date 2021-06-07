package com.q2ve.suai.ui.loginNavigation.onboarding

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.q2ve.suai.R
import com.q2ve.suai.ui.loginNavigation.LoginNavigationInterface

class OnboardingPagerAdapter (fragment: Fragment, val loginNavigation: LoginNavigationInterface) : FragmentStateAdapter(fragment) {
	override fun getItemCount(): Int = 5

	override fun createFragment(position: Int): Fragment {

		var fragment: OnboardingFragment = when (position) {
			0 -> {
				OnboardingFragment(R.layout.login_onboarding_1, loginNavigation)
			}
			1 -> {
				OnboardingFragment(R.layout.login_onboarding_2, loginNavigation)
			}
			2 -> {
				OnboardingFragment(R.layout.login_onboarding_3, loginNavigation)
			}
			3 -> {
				OnboardingFragment(R.layout.login_onboarding_4, loginNavigation)
			}
			4 -> {
				OnboardingFragment(R.layout.login_onboarding_5, loginNavigation, true)
			}
			else -> {
				OnboardingFragment(R.layout.login_onboarding_1, loginNavigation)
			}
		}

		return fragment
	}

}