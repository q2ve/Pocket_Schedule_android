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
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import com.q2ve.suai.R
import com.q2ve.suai.interfacesRENAME.NavigationInterface
import com.q2ve.suai.ui.bottomMenu.selector.RecyclerInterface
import com.q2ve.suai.ui.bottomMenu.selector.RecyclerSelectorView
import kotlinx.android.synthetic.main.bottom_menu_container.view.*


class BottomMenuView(private val title: String, private val fragmentReplacer: NavigationInterface): Fragment(), RecyclerInterface {
    lateinit var presenter: BottomMenuPresenterInterface

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.bottom_menu_container, container, false)
        val background: LinearLayout = rootView.bottom_menu_container_background
        val menu: LinearLayout = rootView.bottom_menu_container
        val bottomMenuTitle: TextView = rootView.bottom_menu_container_title
        val exitButton: Button = rootView.bottom_menu_container_exit_button

        bottomMenuTitle.text = title

        background.alpha = 0f
        background.translationY = 0f
        ViewCompat.animate(background)
            .alpha(0.7f)
            .setDuration(200)
            .start()
        ViewCompat.animate(menu)
            .translationY(100f)
            .setDuration(400)
            .setInterpolator(DecelerateInterpolator())
            .start()

        exitButton.setOnClickListener{
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
                    presenter.exitButtonPressed()
                }
                .start()
        }

        val test = RecyclerSelectorView(this)
        fragmentReplacer.addFragment(R.id.bottom_menu_recycler_container, test)


        return rootView
    }

    override fun onRecyclerItemClicked(name: String) {
        Log.d("TOAST2", name)
    }
}