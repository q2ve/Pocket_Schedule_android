package com.q2ve.suai.ui.coreNavigation

/**
 * Created by Denis Shishkin on 29.06.2021.
 * qwq2eq@gmail.com
 */

interface CoreNavigationInterface {
	fun goToNews ()
	fun goToSchedule ()
	fun goToDeadlines ()
	fun openSettings ()
	fun getCurrentScreen (): Int
}