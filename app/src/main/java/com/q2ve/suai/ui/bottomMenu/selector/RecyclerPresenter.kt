package com.q2ve.suai.ui.bottomMenu.selector

import android.util.Log
import com.q2ve.suai.helpers.FragmentReplacer
import com.q2ve.suai.helpers.contentGetter.ContentGetter
import com.q2ve.suai.helpers.contentGetter.ContentGetterInterface
import com.q2ve.suai.helpers.realm.objects.IndexerItemType
import com.q2ve.suai.helpers.realm.objects.RealmIdNameInterface

/**
 * Created by Denis Shishkin on 06.07.2021.
 * qwq2eq@gmail.com
 */

interface RecyclerPresenterInterface {
	fun recyclerCallback(realmObject: RealmIdNameInterface)
}

class RecyclerPresenter(
	private val parent: RecyclerPresenterInterface,
	private val recyclerFrame: Int,
	private val contentType: IndexerItemType,
	private val universityID: Int
): RecyclerFragmentInterface, ContentGetterInterface {

	private lateinit var recycler: RecyclerSelectorFragment
	private var searchFlag = false
	private var itemAddingFlag = false
	private var offset = 0
	private val limit = 20
	private var query = ""

	init {
		getItems()
	}

	private fun getItems() {
		when (contentType) {
			IndexerItemType.Groups -> {
				ContentGetter(this).getGroups(offset, limit, universityID, query)
			}
			IndexerItemType.Professors -> {
				ContentGetter(this).getProfessors(offset, limit, universityID, query)
			}
			IndexerItemType.Universities -> {
				ContentGetter(this).getProfessors(offset, limit, universityID, query)
			}
			else -> { /*TODO(Other content types and exception handling)*/ }
		}
	}

	private fun putRecycler(items: List<RealmIdNameInterface>) {
		recycler = RecyclerSelectorFragment(this, items)
		FragmentReplacer.replaceFragment(recyclerFrame, recycler)
	}

	override fun onRecyclerItemClicked(realmObject: RealmIdNameInterface) {
		parent.recyclerCallback(realmObject)
	}

	override fun searchItems(name: String) {
		Log.d("Search this", name)
		query = name
		dropOffset()
		getItems()
	}

	override fun uploadMoreItems() {
		itemAddingFlag = true
		increaseOffset()
		getItems()
	}

	override fun contentGetterCallback(
		objects: List<Any>?,
		isError: Boolean,
		errorMessage: String?
	) {
		if (isError) {
			TODO()
		} else {
			if (itemAddingFlag) {
				itemAddingFlag = false
				recycler.addItems(objects as List<RealmIdNameInterface>)
			} else {
				if (searchFlag) {
					recycler.updateData(objects as List<RealmIdNameInterface>)
				} else {
					putRecycler(objects as List<RealmIdNameInterface>)
					searchFlag = true
				}
			}
		}
	}

	private fun increaseOffset() {
		offset += 20
	}

	private fun dropOffset() {
		offset = 0
	}
}