package com.q2ve.suai.ui.coreNavigation

/**
 * Created by Denis Shishkin on 16.04.2021.
 * qwq2eq@gmail.com
 */

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.q2ve.suai.R
import com.q2ve.suai.helpers.FragmentReplacer
import com.q2ve.suai.helpers.ReplaceAnimation
import com.q2ve.suai.ui.RootNavigationInterface
import com.q2ve.suai.ui.coreNavigation.coreFragments.deadlines.DeadlinesPresenter
import com.q2ve.suai.ui.coreNavigation.coreFragments.deadlines.DeadlinesView
import com.q2ve.suai.ui.coreNavigation.coreFragments.news.NewsPresenter
import com.q2ve.suai.ui.coreNavigation.coreFragments.news.NewsView
import com.q2ve.suai.ui.coreNavigation.coreFragments.schedule.ScheduleFragment
import com.q2ve.suai.ui.coreNavigation.coreFragments.schedule.SchedulePresenter
import com.q2ve.suai.ui.coreNavigation.settings.SettingsPresenter
import com.q2ve.suai.ui.coreNavigation.settings.SettingsView
import kotlinx.android.synthetic.main.core_navigation.view.*

class CoreNavigation(private val rootNavigation: RootNavigationInterface): Fragment(), CoreNavigationInterface {
    private var startScreen = 1
    private var currentScreen = -1
    private lateinit var navbar: CoreNavbar
    private val newsPresenter = NewsPresenter()
    private val schedulePresenter = SchedulePresenter(this)
    private val deadlinesPresenter = DeadlinesPresenter()
    private val settingsPresenter = SettingsPresenter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.core_navigation, container, false)
        navbar = CoreNavbar(this)

        //Adding navbar to core navigation view
        FragmentReplacer.replaceFragment(R.id.core_navbar_frame, navbar)

        val test = rootView.core_navigation_frame
        test.setOnClickListener {
            rootNavigation.goToLoginFragments()
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        when (startScreen) {
            0 -> {
                goToNews()
            }
            1 -> {
                goToSchedule()
            }
            2 -> {
                goToDeadlines()
            }
        }
    }

    private fun animationDirection(targetScreen: Int): ReplaceAnimation {
        return when {
            targetScreen > currentScreen -> {
                ReplaceAnimation.RtL_slide
            }
            targetScreen < currentScreen -> {
                ReplaceAnimation.LtR_slide
            }
            else -> {
                ReplaceAnimation.None
            }
        }
    }

    private fun replaceFragment(fragment: Fragment, animation: ReplaceAnimation) {
        FragmentReplacer.replaceFragment(R.id.core_navigation_frame, fragment, animation)
    }

    override fun goToNews() {
        if (currentScreen != 0) {
            val newsView = NewsView(newsPresenter)
            newsPresenter.view = newsView
            replaceFragment(newsView, animationDirection(0))
            currentScreen = 0
            navbar.newsSelected()
        }
    }

    override fun goToSchedule() {
        if (currentScreen != 1) {
            val scheduleView = ScheduleFragment(schedulePresenter)
            schedulePresenter.fragment = scheduleView
            replaceFragment(scheduleView, animationDirection(1))
            currentScreen = 1
            navbar.scheduleSelected()
        }
    }

    override fun goToDeadlines() {
        if (currentScreen != 2) {
            val deadlinesView = DeadlinesView(deadlinesPresenter)
            deadlinesPresenter.view = deadlinesView
            replaceFragment(deadlinesView, animationDirection(2))
            currentScreen = 2
            navbar.deadlinesSelected()
        }
    }

    override fun openSettings() {
        val settingsView = SettingsView(settingsPresenter)
        settingsPresenter.view = settingsView
        FragmentReplacer.addFragment(R.id.core_navigation_frame, settingsView)
    }

    override fun getCurrentScreen(): Int {
        return currentScreen
    }
}