package com.q2ve.suai.helpers.realm.objects

import com.q2ve.suai.helpers.retrofit.objects.RetrofitEnumDeadlineType
import com.q2ve.suai.helpers.retrofit.objects.RetrofitItemSubject
import io.realm.RealmObject

/**
 * Created by Denis Shishkin on 01.07.2021.
 * qwq2eq@gmail.com
 */
open class RealmDeadlineObject (
	var _id: String = "",
	var title: String = "",
	var description: String,
	var startDate: Int,
	var endDate: Int,
	var isExternal: Boolean,
	var isOutdated: Boolean,
	var type: RetrofitEnumDeadlineType,
	var subject: RetrofitItemSubject,
	var curPoints: Int,
	var markpoint: Int,
	var reportRequired: Boolean
): RealmObject()