package com.q2ve.suai.ui.coreNavigation.coreFragments.schedule

/**
 * Created by Denis Shishkin on 05.07.2021.
 * qwq2eq@gmail.com
 */

enum class ScheduleWeekday {
	Mon, Tue, Wed, Thu, Fri, Sat, None;

	fun getWeekdayName (): String {
		return when (this) {
			Mon -> "Понедельник"
			Tue -> "Вторник"
			Wed -> "Среда"
			Thu -> "Четверг"
			Fri -> "Пятница"
			Sat -> "Суббота"
			None -> "Вне сетки"
		}
	}

	fun getShortWeekdayName (): String {
		return when (this) {
			Mon -> "Пн"
			Tue -> "Вт"
			Wed -> "Ср"
			Thu -> "Чт"
			Fri -> "Пт"
			Sat -> "Сб"
			None -> "Вне сетки"
		}
	}
}