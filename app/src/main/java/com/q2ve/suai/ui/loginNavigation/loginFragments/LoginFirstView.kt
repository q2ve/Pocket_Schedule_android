package com.q2ve.suai.ui.loginNavigation.loginFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.q2ve.suai.R
import kotlinx.android.synthetic.main.login_first.view.*

interface LoginScreenFirstViewInterface {
    fun bindNewUniversityName(name: String)
    fun makeErrorMessage (cleanFlag: Boolean, stringResource: Int)
}

class LoginFirstView: Fragment(), LoginScreenFirstViewInterface {

    lateinit var presenter:LoginScreenFirstPresenterInterface

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.login_first, container, false)

        val enterButton: ImageButton = rootView.login_first_enter_button
        enterButton.setOnClickListener {enterButtonPressed()}

        val vkButton: Button = rootView.login_first_vk_button
        vkButton.setOnClickListener {vkButtonPressed()}

        val backButton: Button = rootView.login_first_back_button
        backButton.setOnClickListener {backButtonPressed()}

        val universitySelectorButton: ImageView = rootView.login_first_university_selector_button
        universitySelectorButton.setOnClickListener {universitySelectorButtonPressed()}

        return rootView
    }

    private fun enterButtonPressed () {
        val universityTextView: TextView = view!!.findViewById(R.id.login_first_university_textview)
        presenter.enterButtonPressed(universityTextView.text.toString())
    }

    private fun vkButtonPressed () {
        presenter.vkButtonPressed(activity as AppCompatActivity)
    }

    private fun backButtonPressed () {
        presenter.backButtonPressed()
    }

    private fun universitySelectorButtonPressed() {
        presenter.universitySelectorButtonPressed()
    }

    override fun bindNewUniversityName(name: String) {
        val universityTextView: TextView = view!!.findViewById(R.id.login_first_university_textview)
        universityTextView.text = name
    }

    override fun makeErrorMessage(cleanFlag: Boolean, stringResource: Int) {
        val errorTextView = view!!.login_first_error_textview
        if (cleanFlag) {
            errorTextView.text = ""
        }
        else {
            errorTextView.text = getString(stringResource)
        }
    }
}