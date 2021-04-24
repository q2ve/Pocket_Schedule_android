package com.q2ve.suai.ui.loginNavigation.loginFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.q2ve.suai.R
import kotlinx.android.synthetic.main.login_first.view.*

class LoginFirstView: Fragment() {

    lateinit var presenter:LoginScreenFirstPresenterInterface

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.login_first, container, false)

        val enterButton: Button = rootView.login_first_enter_button
        enterButton.setOnClickListener {enterButtonPressed()}

        val vkButton: Button = rootView.login_first_vk_button
        vkButton.setOnClickListener {vkButtonPressed()}

        val backButton: Button = rootView.login_first_back_button
        backButton.setOnClickListener {backButtonPressed()}

        presenter.viewCreated()
        return rootView
    }

    private fun enterButtonPressed () {
        presenter.enterButtonPressed()
    }

    private fun vkButtonPressed () {
        presenter.vkButtonPressed()
    }

    private fun backButtonPressed () {
        presenter.backButtonPressed()
    }
}