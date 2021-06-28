package com.q2ve.suai.ui.bottomMenu.selector

import com.q2ve.suai.helpers.realm.objects.RealmIdNameInterface

interface RecyclerInterface {
	fun onRecyclerItemClicked(realmObject: RealmIdNameInterface)
}