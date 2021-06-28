package com.q2ve.suai.ui.loginNavigation.onboarding

import android.os.Bundle
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

		val pageTransformer = ViewPager2.PageTransformer { page, position ->
			page.apply {
				val r = 1- kotlin.math.abs(position)
				page.alpha = 0.25f + r
				page.scaleY = 0.75f + r * 0.25f
				
			}
		}
		viewPager.setPageTransformer(pageTransformer)

		viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
			var currentFragment: Int = 0
			var selectedFragment: Int = 0
			var previousPosition: Float = 0F
			var blockSelectedFragment: Boolean = false
			var previousScrollState: Int = 0

			var startRotation: Float = -65F
			var startTranslationX: Float = 170F
			var startTranslationY: Float = -280F
			//var startTint: Float

			var finalRotation: Float = 0F
			var finalTranslationX: Float = 0F
			var finalTranslationY: Float = 0F
			//var finishTint: Float

			override fun onPageScrolled(
				position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
			) {
				super.onPageScrolled(position, positionOffset, positionOffsetPixels)

				if (position < currentFragment && !blockSelectedFragment) {
					selectedFragment = position
				} else if (!blockSelectedFragment) {
					selectedFragment = currentFragment + 1
				}
				//Log.e("currentFragment", currentFragment.toString())
				//Log.e("position", position.toString())
				//Log.e("selectedFragment", selectedFragment.toString())
				//Log.d("-", "----------------------------------------------------")

				val isSwipedOnLeft = if (previousPosition == 0F && position < currentFragment) {
					false
				} else previousPosition < positionOffset

				//Log.e("position", position.toString())
				//Log.e("positionOffset", positionOffset.toString())

				//Log.e("currentFragment", currentFragment.toString())
				//Log.e("selectedFragment", selectedFragment.toString())
				//Log.d("-", "----------------------------------------------------")

				if (isSwipedOnLeft) {
					val rotation = startRotation + (finalRotation - startRotation) * positionOffset
					val translationX = startTranslationX + (finalTranslationX - startTranslationX) * positionOffset
					val translationY = startTranslationY + (finalTranslationY - startTranslationY) * positionOffset
					parent.changeEllipseProperties(translationX, translationY, rotation)
				} else {
					val rotation = finalRotation + (startRotation - finalRotation) * positionOffset
					val translationX = finalTranslationX + (startTranslationX - finalTranslationX) * positionOffset
					val translationY = finalTranslationY + (startTranslationY - finalTranslationY) * positionOffset
					parent.changeEllipseProperties(translationX, translationY, rotation)
				}

				//Log.e("rotation", rotation.toString())
				//Log.e("translationX", translationX.toString())
				//Log.e("translationY", translationY.toString())
				//Log.d("-", "----------------------------------------------------")

				if (!blockSelectedFragment) {
					updateParameters()
				}

				previousPosition = positionOffset
			}

			fun updateParameters () {
				when (currentFragment) {
					0 -> {
						startRotation = -65F
						startTranslationX = 170F
						startTranslationY = -280F
					}
					1 -> {
						startRotation = -65F
						startTranslationX = -150F
						startTranslationY = 110F
					}
					2 -> {
						startRotation = 60F
						startTranslationX = -165F
						startTranslationY = -120F
					}
					3 -> {
						startRotation = -50F
						startTranslationX = -60F
						startTranslationY = -20F
					}
					else -> {
						startRotation = 55F
						startTranslationX = 80F
						startTranslationY = 120F
					}
				}
				when (selectedFragment) {
					0 -> {
						finalRotation = -65F
						finalTranslationX = 170F
						finalTranslationY = -280F
					}
					1 -> {
						finalRotation = -65F
						finalTranslationX = -150F
						finalTranslationY = 110F
					}
					2 -> {
						finalRotation = 60F
						finalTranslationX = -165F
						finalTranslationY = -120F
					}
					3 -> {
						finalRotation = -50F
						finalTranslationX = -60F
						finalTranslationY = -20F
					}
					else -> {
						finalRotation = 55F
						finalTranslationX = 80F
						finalTranslationY = 120F
					}
				}
			}

			override fun onPageSelected(position: Int) {
				super.onPageSelected(position)
				currentFragment = position
				//Log.d("-", "----------------------------------------------------")
				//Log.d("SELECTED", "=================")
				//Log.d("-", "----------------------------------------------------")
			}

			override fun onPageScrollStateChanged(state: Int) {
				super.onPageScrollStateChanged(state)
				if (state == 2) {
					blockSelectedFragment = true
					//Log.d("-", "----------------------------------------------------")
					//Log.d("RELEASED", "=================")
					//Log.d("-", "----------------------------------------------------")
				} else {
					blockSelectedFragment = false
				}

				if (previousScrollState == 2 && state == 0) {
					//Log.e("updateParameters", UUID.randomUUID().toString())
				}

				previousScrollState = state
			}
		})

		return rootView
	}
}