package com.q2ve.suai.ui

/**
 * Created by Denis Shishkin on 15.04.2021.
 * qwq2eq@gmail.com
 */

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.q2ve.suai.R
import com.q2ve.suai.helpers.FragmentReplacer
import com.q2ve.suai.ui.coreNavigation.CoreNavigation
import com.q2ve.suai.ui.loginNavigation.LoginNavigation

class RootNavigation(): Fragment(), RootNavigationInterface {

    //private val loginNavigation = LoginNavigation(this)
    //private val coreNavigation =  CoreNavigation(this)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.root_navigation, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goToCoreFragments()
    }

    override fun goToCoreFragments() {
        FragmentReplacer.replaceFragment(R.id.root_navigation_frame, CoreNavigation(this))
    }

    override fun goToLoginFragments() {
        FragmentReplacer.replaceFragment(R.id.root_navigation_frame, LoginNavigation(this))
    }
}