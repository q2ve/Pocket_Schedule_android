package com.q2ve.suai.helpers.realm.objects

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by Denis Shishkin on 01.07.2021.
 * qwq2eq@gmail.com
 */

open class RealmSubjectObject (
	@PrimaryKey
	var _id: String = "",
	var name: String = "",
	var deadlines: List<RealmDeadlineObject>? = null
): RealmObject()