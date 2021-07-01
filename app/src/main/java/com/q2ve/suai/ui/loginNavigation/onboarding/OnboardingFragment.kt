package com.q2ve.suai.ui.loginNavigation.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.q2ve.suai.R
import com.q2ve.suai.ui.loginNavigation.LoginNavigationInterface

class OnboardingFragment (val layout: Int, val loginNavigation: LoginNavigationInterface, val isFinal: Boolean = false): Fragment() {

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val rootView = inflater.inflate(layout, container, false)

		if (isFinal) {
			val authoriseButton: Button = rootView.findViewById(R.id.login_onboarding_authorize_button)
			authoriseButton.setOnClickListener{
				loginNavigation.goToFirstScreen(isFromOnboarding = true)
			}

			val noAuthoriseButton: Button = rootView.findViewById(R.id.login_onboarding_no_authorize_button)
			noAuthoriseButton.setOnClickListener{
				loginNavigation.goToCoreFragments()
			}
		}

		return rootView
	}
}