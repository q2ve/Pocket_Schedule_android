package com.q2ve.suai.helpers.realm.objects

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RealmScheduleUserObject(
	@PrimaryKey
	var _id: String = "",
	var name: String = ""
): RealmObject(), RealmIdNameInterface {

	override fun getTheId(): String {
		return _id
	}

	override fun getTheName(): String {
		return name
	}
}