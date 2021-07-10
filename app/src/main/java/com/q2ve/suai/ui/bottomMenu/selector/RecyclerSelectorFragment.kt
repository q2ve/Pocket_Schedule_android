package com.q2ve.suai.ui.bottomMenu.selector

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.q2ve.suai.R
import com.q2ve.suai.helpers.realm.objects.RealmIdNameInterface
import kotlinx.android.synthetic.main.recycler_selector.view.*

interface RecyclerFragmentInterface {
	fun onRecyclerItemClicked(realmObject: RealmIdNameInterface)
	fun searchItems(name: String)
	fun uploadMoreItems()
}

class RecyclerSelectorFragment(private val parent: RecyclerFragmentInterface, private val inputData: List<RealmIdNameInterface>): Fragment() {
	private val recyclerAdapter = RecyclerSelectorAdapter(inputData, parent)
	private var allowAddingItems = true
	private var previousItemCount = 20
	private var isGroupsSelected = true

		override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val rootView = inflater.inflate(R.layout.recycler_selector, container, false)

		//Initializing RecyclerView
		initRecycler(inputData, rootView)

		//Initializing search field, adding change listener
		val searchField = rootView.recycler_view_selector_search_field
		val cancelButton = rootView.recycler_view_selector_cancel_button
		searchField.addTextChangedListener(object: TextWatcher {
			override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

			override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
				if (s != null) {
					Log.d("s", s.toString())
					parent.searchItems(s.toString())
					cancelButton.isVisible = true
					cancelButton.setOnClickListener { searchField.editableText.clear() }
					if (s.isEmpty()) { cancelButton.isVisible = false }
				}
			}
			override fun afterTextChanged(s: Editable?) { }
		})

		return rootView
	}

	private fun initRecycler (data: List<RealmIdNameInterface>, currentView: View) {
		val recyclerView: RecyclerView = currentView.recycler_recyclerview
		val layoutManager = LinearLayoutManager(activity)
		recyclerView.layoutManager = layoutManager
		recyclerView.adapter = recyclerAdapter
		recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
			override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
				super.onScrolled(recyclerView, dx, dy)
				val itemCount = recyclerView.adapter!!.itemCount
				if (layoutManager.findLastVisibleItemPosition() >= itemCount - 5) {
					if (allowAddingItems) {
						Log.d("Uploading", layoutManager.findLastVisibleItemPosition().toString())
						view!!.recycler_uploading_progressbar.visibility = View.VISIBLE
						parent.uploadMoreItems()
						allowAddingItems = false
					}
					if (itemCount > previousItemCount)	{
						allowAddingItems = true
					}
					previousItemCount = itemCount
				}
			}
		})
	}

	fun updateData (data: List<RealmIdNameInterface>) {
		allowAddingItems = false
		recyclerAdapter.data = data
		recyclerAdapter.notifyDataSetChanged()
	}

	fun addItems (data: List<RealmIdNameInterface>) {
		allowAddingItems = false
		view!!.recycler_uploading_progressbar.visibility = View.GONE
		recyclerAdapter.data += data
		recyclerAdapter.notifyDataSetChanged()
	}
}