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
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.q2ve.suai.R
import com.q2ve.suai.helpers.contentGetter.ContentGetterInterface

class ScheduleView (private val presenter: SchedulePresenterInterface): Fragment(), ScheduleViewInterface, ContentGetterInterface {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.core_schedule, container, false)
        //ContentGetter(this).getLessons("1e0e30726e7f72a88c70ec82e7b5d97844cf3763c068d37a537a11d3a02b93e3")
        return rootView
    }

    fun changeTitle (title: String) {
        view!!.findViewById<TextView>(R.id.core_schedule_title).text = title
    }

    override fun contentGetterCallback(
        objects: List<Any>?,
        isError: Boolean,
        errorMessage: String?
    ) {
        Log.d("Lessons", objects.toString())
    }
}