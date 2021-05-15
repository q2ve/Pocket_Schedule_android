package com.q2ve.suai.ui.loginNavigation.loginFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.q2ve.suai.R
import kotlinx.android.synthetic.main.login_second.view.*

class LoginSecondView: Fragment() {

    lateinit var presenter:LoginScreenSecondPresenterInterface

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.login_second, container, false)

        val loginField = rootView.login_second_login_field
        val passwordField = rootView.login_second_password_field

        val enterButton: Button = rootView.login_second_enter_button
        enterButton.setOnClickListener {
            enterButtonPressed(loginField.text.toString(), passwordField.text.toString())
        }

        val backButton: Button = rootView.login_second_back_button
        backButton.setOnClickListener {backButtonPressed()}

        presenter.viewCreated()
        return rootView
    }

    private fun enterButtonPressed (login: String, password: String) {
        presenter.enterButtonPressed(login, password)
    }

    private fun backButtonPressed () {
        presenter.backButtonPressed()
    }
}