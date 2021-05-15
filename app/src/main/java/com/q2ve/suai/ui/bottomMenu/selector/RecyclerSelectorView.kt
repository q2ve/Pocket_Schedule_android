package com.q2ve.suai.ui.bottomMenu.selector

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.q2ve.suai.R
import kotlinx.android.synthetic.main.bottom_menu_recycler_selector.view.*

class RecyclerSelectorView(private val parent: RecyclerInterface): Fragment()  {
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val rootView = inflater.inflate(R.layout.bottom_menu_recycler_selector, container, false)

		val recyclerView: RecyclerView = rootView.recycler_recyclerView
		recyclerView.layoutManager = LinearLayoutManager(activity)

		val dummy: List<String> = listOf("Институт Теплых Мужских Отношений", "Лоликек", "Бонч", "ЦПШ", "Хуй", "Хуй", "Хуй", "Хуй", "Хуй", "Хуй", "Хуй", "Хуй", "Хуй", "Хуй", "Хуй", "Хуй", "Хуй", "Хуй", "Хуй")

		recyclerView.adapter = RecyclerSelectorAdapter(dummy, parent)





		return rootView
	}
}