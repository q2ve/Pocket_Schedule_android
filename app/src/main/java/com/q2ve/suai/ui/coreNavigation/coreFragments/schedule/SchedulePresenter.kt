package com.q2ve.suai.ui.coreNavigation.coreFragments.schedule

import android.util.Log
import com.q2ve.suai.R
import com.q2ve.suai.helpers.FragmentReplacer
import com.q2ve.suai.helpers.contentGetter.ContentGetter
import com.q2ve.suai.helpers.contentGetter.ContentGetterInterface
import com.q2ve.suai.helpers.realm.objects.IndexerItemType
import com.q2ve.suai.helpers.realm.objects.RealmIdNameInterface
import com.q2ve.suai.helpers.realm.objects.RealmLessonObject
import com.q2ve.suai.ui.bottomMenu.BottomMenuFragment
import com.q2ve.suai.ui.bottomMenu.BottomMenuInterface
import com.q2ve.suai.ui.bottomMenu.BottomMenuPresenter
import com.q2ve.suai.ui.bottomMenu.selector.RecyclerPresenter
import com.q2ve.suai.ui.bottomMenu.selector.RecyclerPresenterInterface
import com.q2ve.suai.ui.coreNavigation.CoreNavigationInterface
import java.util.*

/**
 * Created by Denis Shishkin on 16.04.2021.
 * qwq2eq@gmail.com
 */

//TODO(Exceptions handling)
//TODO(Add university setter)
class SchedulePresenter(private val coreNavigation: CoreNavigationInterface): SchedulePresenterInterface, ContentGetterInterface, BottomMenuInterface, RecyclerPresenterInterface {
	lateinit var fragment: ScheduleFragmentInterface

	private val bottomMenuView = BottomMenuFragment("Выбор хуйни")
	private val bottomMenuPresenter = BottomMenuPresenter(bottomMenuView, this)

	private lateinit var lessons: List<RealmLessonObject>
	private var filterIsWeekOdd = true
	private var filterWeekday = ScheduleWeekday.Mon
	private var isBottomMenuOpened = false


	override fun filterButtonPressed() {
		bottomMenuView.presenter = bottomMenuPresenter
		FragmentReplacer.addFragment(R.id.core_navigation_bottom_menu_frame, bottomMenuView)
		RecyclerPresenter(this, R.id.bottom_menu_recycler_container, IndexerItemType.Groups, 1)
	}

	override fun recyclerCallback(realmObject: RealmIdNameInterface) {
		bottomMenuView.exitAnimation()
		Log.d("recyclerCallback", realmObject.toString())
		fragment.initialSchedulePlacement(realmObject.getTheId())
		fragment.changeTitle(realmObject.getTheName())
	}

	override fun bottomMenuClosed() {
		isBottomMenuOpened = false
	}

	override fun getLessons(id: String, weekday: ScheduleWeekday, isWeekOdd: Boolean, uploadLessons: Boolean) {
		if (uploadLessons) {
			ContentGetter(this).getLessons(id)
			filterIsWeekOdd = isWeekOdd
			filterWeekday = weekday
		}
		else {
			replaceScheduleDay(weekday, isWeekOdd)
		}
	}

	private fun replaceScheduleDay(weekday: ScheduleWeekday, isWeekOdd: Boolean) {
		val sortedList = sortRealmLessonObjects(lessons, weekday, isWeekOdd)
		fragment.replaceScheduleDay(convertRealmLessonObjects(sortedList))
	}

	private fun sortRealmLessonObjects(objects: List<RealmLessonObject>, weekday: ScheduleWeekday, isWeekOdd: Boolean): List<RealmLessonObject> {
		val dayOfTheWeek = weekday.toString().toLowerCase(Locale.ROOT)
		val weekParity = when (isWeekOdd) {
			true -> { "odd" }
			false -> { "even" }
		}
		var output: Array<RealmLessonObject> = emptyArray()
		objects.forEach { item ->
			if (item.day == dayOfTheWeek && item.week == weekParity) {
				output += item
			}
		}
		output.sortBy { it.lessonNum }
		return output.toList()
	}

	private fun convertRealmLessonObjects(objects: List<RealmLessonObject>): Array<ScheduleLessonData> {
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
			if (it.startTime == null || it.endTime == null) { tags += "Время не назначено" }

			val startTime = when {
				(it.startTime!!.length < 5) -> { "0" + it.startTime!! }
				(it.startTime == null) -> { "xx:xx" }
				else -> { it.startTime!! }
			}

			val endTime = when {
				(it.endTime!!.length < 5) -> { "0" + it.endTime!! }
				(it.endTime == null) -> { "xx:xx" }
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
		lessons = objects!! as List<RealmLessonObject>

		replaceScheduleDay(filterWeekday, filterIsWeekOdd)
	}
}