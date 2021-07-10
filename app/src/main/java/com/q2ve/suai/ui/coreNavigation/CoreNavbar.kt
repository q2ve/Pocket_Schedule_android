package com.q2ve.suai.ui.coreNavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageButton
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import com.q2ve.suai.R
import kotlinx.android.synthetic.main.core_navbar.view.*

/**
 * Created by Denis Shishkin on 29.06.2021.
 * qwq2eq@gmail.com
 */

class CoreNavbar(private val parent: CoreNavigationInterface): Fragment() {
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val rootView = inflater.inflate(R.layout.core_navbar, container, false)

		val newsButton = rootView.core_navbar_news_button
		newsButton.setOnClickListener {
			parent.goToNews()
		}

		val scheduleButton = rootView.core_navbar_schedule_button
		scheduleButton.setOnClickListener {
			parent.goToSchedule()
		}

		val deadlinesButton = rootView.core_navbar_deadlines_button
		deadlinesButton.setOnClickListener {
			parent.goToDeadlines()
		}

		deselectAllButtons(rootView)
		when (parent.getCurrentScreen()) {
			0 -> { newsSelected(rootView) }
			1 -> { scheduleSelected(rootView) }
			2 -> { deadlinesSelected(rootView) }
		}

		return rootView
	}

	fun newsSelected(currentView: View? = view) {
		if (currentView != null) {
			deselectAllButtons(currentView)
			val button = currentView.core_navbar_news_button
			animateButton(button)
		}
	}

	fun scheduleSelected(currentView: View? = view) {
		if (currentView != null) {
			deselectAllButtons(currentView)
			val button = currentView.core_navbar_schedule_button
			animateButton(button)
		}
	}

	fun deadlinesSelected(currentView: View? = view) {
		if (currentView != null) {
			deselectAllButtons(currentView)
			val button = currentView.core_navbar_deadlines_button
			animateButton(button)
		}
	}

	private fun deselectAllButtons(currentView: View? = view!!) {
		if (currentView != null) {
			fun deselect(button: ImageButton) {
				button.drawable.setTint(resources.getColor(R.color.colorLightGray1, activity!!.theme))
			}
			deselect(currentView.core_navbar_news_button)
			deselect(currentView.core_navbar_schedule_button)
			deselect(currentView.core_navbar_deadlines_button)
		}
	}

	private fun animateButton(button: ImageButton) {
		ViewCompat.animate(button)
			.scaleXBy(-0.2f)
			.scaleYBy(-0.2f)
			.setInterpolator(AccelerateDecelerateInterpolator())
			.setDuration(70)
			.withEndAction {
				ViewCompat.animate(button)
					.scaleXBy(0.2f)
					.scaleYBy(0.2f)
					.setInterpolator(AccelerateDecelerateInterpolator())
					.setDuration(70)
					.withEndAction {
						button.drawable.setTint(resources.getColor(R.color.colorBlue, activity!!.theme))
					}
					.start()
			}
			.start()
	}
}