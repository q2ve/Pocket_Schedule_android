package com.q2ve.suai.ui.bottomMenu.selector

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.q2ve.suai.R

class RecyclerSelectorAdapter(private val names: List<String>, private val parent: RecyclerInterface):RecyclerView.Adapter<RecyclerSelectorAdapter.RecyclerItemHolder>() {

	class RecyclerItemHolder(item: View): RecyclerView.ViewHolder(item) {
		var name: TextView = item.findViewById(R.id.recycler_item_text)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemHolder {
		val itemView = LayoutInflater.from(parent.context).inflate(R.layout.bottom_menu_recycler_item, parent, false)
		return RecyclerItemHolder(itemView)
	}

	override fun onBindViewHolder(holder: RecyclerItemHolder, position: Int) {
		holder.name.text = names[position]
		holder.itemView.setOnClickListener {
			Log.d("TOAST1", holder.name.text.toString())
			parent.onRecyclerItemClicked(holder.name.text.toString())
		}
	}

	override fun getItemCount(): Int {
		return names.size
	}
}