package com.q2ve.suai.ui.bottomMenu.selector

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.q2ve.suai.R
import com.q2ve.suai.helpers.realm.objects.RealmIdNameInterface

class RecyclerSelectorAdapter(private val objects: List<RealmIdNameInterface>, private val parent: RecyclerInterface):RecyclerView.Adapter<RecyclerSelectorAdapter.RecyclerItemHolder>() {

	class RecyclerItemHolder(item: View): RecyclerView.ViewHolder(item) {
		var name: TextView = item.findViewById(R.id.recycler_item_text)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemHolder {
		val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
		return RecyclerItemHolder(itemView)
	}

	override fun onBindViewHolder(holder: RecyclerItemHolder, position: Int) {
		//Log.d("Recycler objects", objects.toString())

		holder.name.text = objects[position].getTheName()

		holder.itemView.setOnClickListener {
			//Log.d("Recycler item selected", holder.name.text.toString())
			parent.onRecyclerItemClicked(objects[position])
		}
	}

	override fun getItemCount(): Int {
		return objects.size
	}
}