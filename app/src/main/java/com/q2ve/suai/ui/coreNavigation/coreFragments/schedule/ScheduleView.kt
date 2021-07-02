package com.q2ve.suai.ui.coreNavigation.coreFragments.schedule

/**
 * Created by Denis Shishkin on 16.04.2021.
 * qwq2eq@gmail.com
 */

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.q2ve.suai.R
import kotlinx.android.synthetic.main.core_schedule.view.*
import kotlinx.android.synthetic.main.core_schedule_item.view.*
import kotlinx.android.synthetic.main.core_schedule_item_tag.view.*

class ScheduleView (private val presenter: SchedulePresenterInterface): Fragment(), ScheduleViewInterface {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.core_schedule, container, false)
        return rootView
    }

    override fun changeTitle (title: String) {
        view!!.core_schedule_title.text = title
    }

    override fun replaceScheduleDay(lessons: Array<ScheduleLessonData>) {
        val scheduleModule = view!!.core_schedule_module_frame
        scheduleModule.removeAllViews()
        view!!.core_schedule_module_scroll.isVisible = true

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
                this.onLessonClick(item._id)
            }
        }
    }

    private fun onLessonClick(_id: String) {
        Log.d("Lesson clicked!", _id)
    }
}