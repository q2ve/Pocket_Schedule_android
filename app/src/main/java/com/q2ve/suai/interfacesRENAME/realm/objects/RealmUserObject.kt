package com.q2ve.suai.interfacesRENAME.realm.objects

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
	var group:RealmGroupObject? = null,
	var scheduleUser: RealmScheduleUserObject? = null
): RealmObject()