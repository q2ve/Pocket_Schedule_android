package com.q2ve.suai.ui.bottomMenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.q2ve.suai.R

class BottomMenuView: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.bottom_menu, container, false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}