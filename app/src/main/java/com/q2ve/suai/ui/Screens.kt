package com.q2ve.suai.ui

import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun rootNavigation(fragment: Fragment) = FragmentScreen {fragment}
    fun loginNavigation(fragment: Fragment) = FragmentScreen {fragment}
    fun loginFirst(fragment: Fragment) = FragmentScreen {fragment}
}