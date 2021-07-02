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
        var currentGroupTEST = 0
        rootView.core_schedule_action_bar.setOnClickListener {
            currentGroupTEST = when (currentGroupTEST) {
                0 -> {
                    presenter.test("70aa5c2396487bbed16664156e0edb104466e500d7420b1a17eab4bf52e4f12b")
                    1
                }
                1 -> {
                    presenter.test("d7444145d626b44c7d8306401014b75389a09679188b62320e957df0f5adf84d")
                    2
                }
                2 -> {
                    presenter.test("dd5c133522c5c6fe29b6e1a74a384755534c453ece307b755a941c9df26ac091")
                    3
                }
                else -> {
                    presenter.test("1e0e30726e7f72a88c70ec82e7b5d97844cf3763c068d37a537a11d3a02b93e3")
                    0
                }
            }
        }
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