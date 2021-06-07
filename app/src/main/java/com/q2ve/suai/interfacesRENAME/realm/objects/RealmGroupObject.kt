package com.q2ve.suai.interfacesRENAME.realm.objects

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RealmGroupObject(
	@PrimaryKey
	var _id: String = "",
	var name: String = "",
	var university: RealmUniversityObject? = null
): RealmObject()