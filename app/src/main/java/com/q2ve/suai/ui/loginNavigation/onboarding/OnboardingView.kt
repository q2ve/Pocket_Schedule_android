package com.q2ve.suai.ui.loginNavigation.onboarding

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.q2ve.suai.R
import com.q2ve.suai.ui.loginNavigation.LoginNavigationInterface

class OnboardingView(private val parent: LoginNavigationInterface): Fragment() {

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val rootView = inflater.inflate(R.layout.login_onboarding, container, false)

		val adapter = OnboardingPagerAdapter(this, parent)
		val viewPager: ViewPager2 = rootView.findViewById(R.id.login_onboarding_pager)
		viewPager.adapter = adapter
		/*val pageTransformer = ViewPager2.PageTransformer { page, position ->
			page.apply {
				val r = 1- abs(position)
				page.alpha = 0.25f + r
				page.scaleY = 0.75f + r * 0.25f
				
			}
		}
		viewPager.setPageTransformer(pageTransformer)*/
		viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
			var previousPositionOffset: Float = 0f
			override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
				super.onPageScrolled(position, positionOffset, positionOffsetPixels)
				Log.d("positionOffset is", positionOffset.toString())
				//Log.d("positionOffsetPixels is", positionOffsetPixels.toString())
				//Log.d("position is", position.toString())
				if (positionOffset>previousPositionOffset) {
					parent.changeEllipseProperties(1f, 1f, 1f)
				} else {
					parent.changeEllipseProperties(-1f, -1f, -1f)
				}
				previousPositionOffset = positionOffset
			}
		})

		return rootView
	}
}