package com.q2ve.suai.helpers.realm.objects

/**
 * Created by Denis Shishkin on 01.07.2021.
 * qwq2eq@gmail.com
 */

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RealmSubjectObject (
	@PrimaryKey
	var _id: String = "",
	var name: String = "",
	var deadlines: RealmList<RealmDeadlineObject>? = RealmList()
): RealmObject()