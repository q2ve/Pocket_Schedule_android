package com.q2ve.suai.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.q2ve.suai.R
import com.q2ve.suai.helpers.FragmentReplacer
import com.q2ve.suai.helpers.NavigationInterface
import com.q2ve.suai.ui.coreNavigation.CoreNavigation
import com.q2ve.suai.ui.loginNavigation.LoginNavigation

class RootNavigation(private val activityLink: FragmentActivity): Fragment() {

    private val fragmentReplacer: NavigationInterface = FragmentReplacer

    private val loginNavigation = LoginNavigation(fragmentReplacer)
    private val coreNavigation =  CoreNavigation()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.root_navigation, container, false)

        fragmentReplacer.replaceFragment(R.id.root_navigation_frame, loginNavigation)

        return rootView
    }
}