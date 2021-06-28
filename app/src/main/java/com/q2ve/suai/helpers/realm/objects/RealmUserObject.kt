package com.q2ve.suai.helpers.realm.objects

/**
* Created by Denis Shishkin on 12.06.2021.
* qwq2eq@gmail.com
*/

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RealmUserObject(
	@PrimaryKey
	var _id: String = "",
	var firstName: String = "",
	var lastName: String = "",
	var serviceLogin: String? = null,
	var servicePassword: String? = null,
	var university: RealmUniversityObject? = null,
	var group: RealmScheduleUserObject? = null,
	var scheduleUser: RealmScheduleUserObject? = null
): RealmObject(), RealmIdNameInterface {

	override fun getTheId(): String {
		return _id
	}

	override fun getTheName(): String {
		return "$firstName $lastName"
	}
}