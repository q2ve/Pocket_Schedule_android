package com.q2ve.suai.ui.coreNavigation.coreFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.q2ve.suai.R

class CoreFragmentsNavigation: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.core_fragments_navigation, container, false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}