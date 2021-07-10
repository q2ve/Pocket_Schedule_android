package com.q2ve.suai.ui.coreNavigation.coreFragments.schedule

/**
 * Created by Denis Shishkin on 29.06.2021.
 * qwq2eq@gmail.com
 */

interface SchedulePresenterInterface {
	fun getLessons(id: String, weekday: ScheduleWeekday, isWeekOdd: Boolean, uploadLessons: Boolean = false)
	fun filterButtonPressed ()
}