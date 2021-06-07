package com.q2ve.suai.interfacesRENAME.realm.objects

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RealmUniversityObject(
	@PrimaryKey
	var _id: String = "",
	var name: String = ""
): RealmObject()