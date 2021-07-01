package com.q2ve.suai.ui.coreNavigation.coreFragments.deadlines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.q2ve.suai.R

class DeadlinesView(private val presenter: DeadlinesPresenterInterface): Fragment(), DeadlinesViewInterface {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.core_deadlines, container, false)
        return rootView
    }

}