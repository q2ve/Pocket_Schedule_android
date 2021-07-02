package com.q2ve.suai.ui.coreNavigation.coreFragments.schedule

import com.q2ve.suai.helpers.contentGetter.ContentGetter
import com.q2ve.suai.helpers.contentGetter.ContentGetterInterface
import com.q2ve.suai.helpers.realm.objects.RealmLessonObject

/**
 * Created by Denis Shishkin on 16.04.2021.
 * qwq2eq@gmail.com
 */

class SchedulePresenter: SchedulePresenterInterface, ContentGetterInterface {
	lateinit var view: ScheduleViewInterface
	lateinit var lessons: List<RealmLessonObject>

	init {
		test("1e0e30726e7f72a88c70ec82e7b5d97844cf3763c068d37a537a11d3a02b93e3")
	}

	override fun test(id: String) {
		ContentGetter(this).getLessons(id)
	}

	fun updateLessons(objects: List<RealmLessonObject>) {
		lessons = objects
	}

	fun convertRealmLessonObjects(objects: List<RealmLessonObject>): Array<ScheduleLessonData> {
		fun lessonType(input: String): String {
			return when (input) {
				"lecture" -> { "Лекция" }
				"lab" -> { "Лабораторная работа" }
				"practice" -> { "Практика" }
				"test" -> { "Тест" }
				"course" -> { "Курс" }
				else -> { "Нетипированная" }
				//TODO (Вынести в strings)
			}
		}
		fun pluralizeGroups(count: Int): String {
			return when {
				(count < 1) -> { "Группы не назначены" }
				(count == 1) -> { "$count группа" }
				(count in 2..4) -> { "$count группы" }
				(count in 5..20) -> { "$count групп" }
				else -> { "Что-то дофига групп" }
			}
		}

		var output: Array<ScheduleLessonData> = emptyArray()
		objects.forEach {
			var professors = ""
			it.professors!!.forEach { professor ->
				val professorName = professor.name
				if (professors == "") { professors = professorName }
				else { professors += ", $professorName" }
			}

			var tags = emptyList<String>()
			tags += it.rooms
			tags += lessonType(it.type!!)
			if (it.groups!!.size != 1) { tags += pluralizeGroups(it.groups!!.size) }

			val startTime = when {
				(it.startTime!!.length < 5) -> { "0" + it.startTime!! }
				else -> { it.startTime!! }
			}

			val endTime = when {
				(it.endTime!!.length < 5) -> { "0" + it.endTime!! }
				else -> { it.endTime!! }
			}

			val item = ScheduleLessonData(
				it._id,
				startTime,
				endTime,
				it.lessonNum,
				it.subject!!.name,
				professors,
				tags
			)

			output += item
		}
		return output
	}

	override fun contentGetterCallback(
		objects: List<Any>?,
		isError: Boolean,
		errorMessage: String?
	) {
		var output: Array<RealmLessonObject> = emptyArray()
		(objects!! as List<RealmLessonObject>).forEach { item ->
			if (item.day == "wed" && item.week == "odd") {
				output += item
			}
			output.sortBy { it.lessonNum }
			view.replaceScheduleDay(convertRealmLessonObjects(output.toList()))
		}
	}
}