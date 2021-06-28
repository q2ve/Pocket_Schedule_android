package com.q2ve.suai.ui.bottomMenu

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import com.q2ve.suai.R
import kotlinx.android.synthetic.main.bottom_menu_container.view.bottom_menu_container
import kotlinx.android.synthetic.main.bottom_menu_container.view.bottom_menu_container_background
import kotlinx.android.synthetic.main.bottom_menu_container.view.bottom_menu_container_exit_button
import kotlinx.android.synthetic.main.bottom_menu_container.view.bottom_menu_container_title
import kotlinx.android.synthetic.main.bottom_menu_container_v2.view.*


class BottomMenuView(private val title: String): Fragment() {
    lateinit var presenter: BottomMenuPresenterInterface

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.bottom_menu_container_v2, container, false)

        var isMenuFullyOpened = false

        val bottomMenuTitle: TextView = rootView.bottom_menu_container_title

        val menu: MotionLayout = rootView.bottom_menu_motion_layout
        menu.setTransitionListener(
            object: MotionLayout.TransitionListener {
                override fun onTransitionStarted(
                    motionLayout: MotionLayout?,
                    startId: Int,
                    endId: Int
                ) {}

                override fun onTransitionChange(
                    motionLayout: MotionLayout?,
                    startId: Int,
                    endId: Int,
                    progress: Float
                ) {
                    Log.d("Motion", progress.toString())
                    if (progress > 0.9) {
                        exitAnimation()
                    }
                }

                override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {}

                override fun onTransitionTrigger(
                    motionLayout: MotionLayout?,
                    triggerId: Int,
                    positive: Boolean,
                    progress: Float
                ) {}
            }
        )

        val background: LinearLayout = rootView.bottom_menu_container_background
        background.setOnClickListener{
            if (isMenuFullyOpened) {
                exitAnimation()
            }

        }

        val exitButton: Button = rootView.bottom_menu_container_exit_button
        exitButton.setOnClickListener{
            if (isMenuFullyOpened) {
                exitAnimation()
            }
        }

        bottomMenuTitle.text = title

        background.alpha = 0f
        background.translationY = 0f
        ViewCompat.animate(background) //Anti-lag shit, does nothing but delay
            .alpha(0.0f)
            .setDuration(120)
            .withEndAction {
                ViewCompat.animate(background)
                    .alpha(0.7f)
                    .setDuration(400)
                    .start()
                ViewCompat.animate(menu)
                    .translationY(0f)
                    .setDuration(600)
                    .setInterpolator(DecelerateInterpolator())
                    .withEndAction{ isMenuFullyOpened = true }
                    .start()
            }
            .start()

        return rootView
    }

    fun exitAnimation() {
        val background: LinearLayout = view!!.bottom_menu_container_background
        val menu: LinearLayout = view!!.bottom_menu_container

        background.alpha = 0.7f
        ViewCompat.animate(background)
            .alpha(0f)
            .setDuration(300)
            .setInterpolator(AccelerateInterpolator())
            .start()
        ViewCompat.animate(menu)
            .translationY(3000f)
            .setDuration(300)
            .setInterpolator(AccelerateInterpolator())
            .withEndAction {
                background.translationY = 3000f
                presenter.exitBottomMenu()
            }
            .start()
    }
}