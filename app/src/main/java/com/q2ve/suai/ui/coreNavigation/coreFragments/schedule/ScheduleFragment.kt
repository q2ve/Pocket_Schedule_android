package com.q2ve.suai.ui.coreNavigation.coreFragments.schedule

/**
 * Created by Denis Shishkin on 16.04.2021.
 * qwq2eq@gmail.com
 */

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.q2ve.suai.R
import kotlinx.android.synthetic.main.core_schedule.view.*
import kotlinx.android.synthetic.main.core_schedule_empty_item.view.*
import kotlinx.android.synthetic.main.core_schedule_item.view.*
import kotlinx.android.synthetic.main.core_schedule_item_tag.view.*
import kotlinx.android.synthetic.main.core_schedule_weekday.view.*
import java.text.SimpleDateFormat
import java.util.*

//TODO(User selection with options button)
//TODO(Days swiping)
//TODO(Deadlines tags)
//TODO(Lesson card)
class ScheduleFragment (private val presenter: SchedulePresenterInterface): Fragment(), ScheduleFragmentInterface {
    private var selectedWeekdays: List<View> = emptyList()
    private var selectedDay: ScheduleWeekday = ScheduleWeekday.Mon
    private var isOddWeekSelected: Boolean = false //TODO(Add week parity setter)
    private var scheduleUser = "dea6805b80f437209aba90b926424306220c9bf8d6070e5d13e8fdb1ab251b02" //TODO(Add start schedule user setter)

    private var defaultBackgroundColor = 0
    private var defaultTextColor = 0
    private var weekBackgroundColor = 0
    private var weekTextColor = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.core_schedule, container, false)

        defaultBackgroundColor = resources.getColor(R.color.colorPlaqueBackground, activity!!.theme)
        defaultTextColor = resources.getColor(R.color.colorLightGray1, activity!!.theme)
        weekBackgroundColor = resources.getColor(R.color.colorBlueLight, activity!!.theme)
        weekTextColor = resources.getColor(R.color.colorBlue, activity!!.theme)

        val weekParityButton = rootView.core_schedule_week_parity
        weekParityButton.setOnClickListener { setWeekParity(rootView, !isOddWeekSelected) }

        val filterButton = rootView.core_schedule_group_filter
        filterButton.setOnClickListener { presenter.filterButtonPressed() }

        putWeekdays(rootView)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialSchedulePlacement(scheduleUser)
    }

    override fun initialSchedulePlacement(selectedScheduleUser: String) {
        val weekday = getCurrentWeekday()
        presenter.getLessons(selectedScheduleUser, weekday, isOddWeekSelected, true)
        uncolorAllWeekdays()
        setWeekdayIndicator(view!!, weekday)
        selectedWeekdays.forEach {
            if ((it as TextView).text == weekday.getShortWeekdayName()) {
                it.core_schedule_weekday.setTextColor(weekTextColor)
                it.core_schedule_weekday.background.setTint(weekBackgroundColor)
                Handler().postDelayed(Runnable { view!!.core_schedule_weekdays_scroll.smoothScrollTo(it.left - 240, 0) }, 200)
            }
        }
        selectedDay = weekday
        scheduleUser = selectedScheduleUser
    }

    private fun getCurrentWeekday (): ScheduleWeekday {
        return when (SimpleDateFormat("EEE", Locale.US).format(Date())) {
            "Mon" -> { ScheduleWeekday.Mon }
            "Tue" -> { ScheduleWeekday.Tue }
            "Wed" -> { ScheduleWeekday.Wed }
            "Thu" -> { ScheduleWeekday.Thu }
            "Fri" -> { ScheduleWeekday.Fri }
            "Sat" -> { ScheduleWeekday.Sat }
            "Sun" -> { ScheduleWeekday.Mon }
            else -> {
                ScheduleWeekday.Mon
            }
        }
    }

    override fun changeTitle(title: String) {
        view!!.core_schedule_title.text = title
    }

    private fun setWeekParity(view: View, isOdd: Boolean) {
        val weekParityButton = view.core_schedule_week_parity
        val backgroundColor: Int
        val textColor: Int

        if (isOdd) {
            backgroundColor = resources.getColor(R.color.colorRedMainLight, activity!!.theme)
            textColor = resources.getColor(R.color.colorDeadlineRed, activity!!.theme)
            weekParityButton.text = resources.getString(R.string.odd)
        } else {
            backgroundColor = weekBackgroundColor
            textColor = weekTextColor
            weekParityButton.text = resources.getString(R.string.even)
        }

        weekParityButton.background.setTint(backgroundColor)
        weekParityButton.setTextColor(textColor)

        isOddWeekSelected = isOdd
        presenter.getLessons(scheduleUser, selectedDay, isOddWeekSelected)
    }

    private fun setWeekdayIndicator(view: View, weekday: ScheduleWeekday) {
        selectedDay = weekday
        view.core_schedule_current_weekday.text = weekday.getWeekdayName()
        if (weekday.toString() == SimpleDateFormat("EEE", Locale.US).format(Date())) {
            showCurrentDayIndicator(view)
        }
        else { hideCurrentDayIndicator(view) }
    }

    private fun showCurrentDayIndicator (view: View) {
        view.core_schedule_current_day_indicator.isVisible = true
    }

    private fun hideCurrentDayIndicator (view: View) {
        view.core_schedule_current_day_indicator.isVisible = false
    }

    private fun uncolorAllWeekdays () {
        selectedWeekdays.forEach { selected ->
            selected.core_schedule_weekday.setTextColor(defaultTextColor)
            selected.core_schedule_weekday.background.setTint(defaultBackgroundColor)
        }
    }

    private fun putWeekdays(view: View) {
        val weekdaysScroll = view.core_schedule_weekdays_layout
        enumValues<ScheduleWeekday>().forEach { weekday ->
            val weekdayView = layoutInflater.inflate(R.layout.core_schedule_weekday, weekdaysScroll, false)
            weekdayView.core_schedule_weekday.text = weekday.getShortWeekdayName()
            weekdaysScroll.addView(weekdayView)
            selectedWeekdays += weekdayView
            weekdayView.setOnClickListener {
                this.onWeekdayClicked(weekday)
                Handler().postDelayed(Runnable { view.core_schedule_weekdays_scroll.smoothScrollTo(it.left - 240, 0) }, 200)
                it.core_schedule_weekday.setTextColor(weekTextColor)
                it.core_schedule_weekday.background.setTint(weekBackgroundColor)
            }
        }
    }

    override fun replaceScheduleDay(lessons: Array<ScheduleLessonData>) {
        val scheduleModule = view!!.core_schedule_module_frame
        scheduleModule.removeAllViews()
        view!!.core_schedule_module_scroll.isVisible = true

        if (lessons.isEmpty()) {
            val emptySchedule = layoutInflater.inflate(R.layout.core_schedule_empty_item, scheduleModule, false)
            val text = if (selectedDay == ScheduleWeekday.None) {
                selectedDay.getWeekdayName() + " " + resources.getString(R.string.there_is_no_lessons)
            } else {
                selectedDay.getWeekdayName() + resources.getString(R.string.comma_there_is_no_lessons)
            }
            emptySchedule.core_schedule_empty_item_text.text = text
            scheduleModule.addView(emptySchedule)
        }

        lessons.forEach { item ->
            val lesson = layoutInflater.inflate(R.layout.core_schedule_item, scheduleModule, false)
            lesson.core_schedule_item_start_time.text = item.startTime
            lesson.core_schedule_item_end_time.text = item.endTime
            lesson.core_schedule_item_index.text = item.lessonNum
            lesson.core_schedule_item_subject.text = item.subject
            lesson.core_schedule_item_professor.text = item.professors
            val tagBox = lesson.core_schedule_item_tag_box
            item.tags.forEach { tag ->
                val tagView = layoutInflater.inflate(R.layout.core_schedule_item_tag, tagBox, false)
                tagView.core_schedule_item_tag.text = tag
                tagBox.addView(tagView)
            }

            scheduleModule.addView(lesson)
            lesson.setOnClickListener {
                this.onLessonClicked(item._id)
            }
        }
    }

    private fun onLessonClicked(_id: String) {
        Log.d("Lesson clicked!", _id)
        //TODO(LessonCard)
    }

    private fun onWeekdayClicked(weekday: ScheduleWeekday) {
        uncolorAllWeekdays()
        setWeekdayIndicator(view!!, weekday)
        presenter.getLessons(scheduleUser, weekday, isOddWeekSelected)
    }
}