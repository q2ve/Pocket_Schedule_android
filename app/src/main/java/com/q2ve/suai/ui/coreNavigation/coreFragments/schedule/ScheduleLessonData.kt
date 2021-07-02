package com.q2ve.suai.ui.coreNavigation.coreFragments.schedule

/**
 * Created by Denis Shishkin on 02.07.2021.
 * qwq2eq@gmail.com
 */

data class ScheduleLessonData (
	val _id: String,
	val startTime: String = "хуй",
	val endTime: String = "хуй",
	val lessonNum: String = "хуй",
	val subject: String = "хуй",
	val professors: String = "хуй",
	val tags: List<String> = listOf("хуй", "хуй", "хуй", "хуй", "хуй", "хуй")
)
