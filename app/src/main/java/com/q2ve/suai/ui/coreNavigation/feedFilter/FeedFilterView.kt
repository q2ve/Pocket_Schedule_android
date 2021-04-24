package com.q2ve.suai.ui.coreNavigation.feedFilter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.q2ve.suai.R

class FeedFilterView: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.core_feed_filter, container, false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}