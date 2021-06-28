package com.q2ve.suai.helpers.realm.objects

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RealmUniversityObject(
	@PrimaryKey
	var _id: String = "",
	var referenceDate: String = "",
	var referenceWeek: String = "",
	var universityName: String = "",
	var universityService: String = ""
): RealmObject(), RealmIdNameInterface {

	override fun getTheId(): String {
		return _id
	}

	override fun getTheName(): String {
		return universityName
	}
}