package com.q2ve.suai.helpers.realm.objects

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by Denis Shishkin on 01.07.2021.
 * qwq2eq@gmail.com
 */

open class RealmDeadlineObject (
	@PrimaryKey
	var _id: String = "",
	var title: String = "",
	var description: String = "",
	var startDate: Int = 0,
	var endDate: Int = 0,
	var isExternal: Boolean = false,
	var isOutdated: Boolean = false,
	var type: String? = null,
	var subject: RealmSubjectObject? = null,
	var curPoints: Int = 0,
	var markpoint: Int = 0,
	var reportRequired: Boolean = false
): RealmObject()