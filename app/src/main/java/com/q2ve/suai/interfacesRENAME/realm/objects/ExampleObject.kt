package com.q2ve.suai.interfacesRENAME.realm.objects

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ExampleObject (
	@PrimaryKey
	var name: String? = null,
	var secondName: String = ""
): RealmObject()