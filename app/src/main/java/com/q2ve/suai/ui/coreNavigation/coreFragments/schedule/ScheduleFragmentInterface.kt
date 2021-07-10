package com.q2ve.suai.ui.coreNavigation.coreFragments.schedule

/**
 * Created by Denis Shishkin on 29.06.2021.
 * qwq2eq@gmail.com
 */

interface ScheduleFragmentInterface {
	fun replaceScheduleDay(lessons: Array<ScheduleLessonData>)
	fun initialSchedulePlacement(selectedScheduleUser: String)
	fun changeTitle(title: String)
}