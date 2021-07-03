package com.q2ve.suai.helpers.retrofit.objects

import com.q2ve.suai.helpers.realm.UniqueImportSource

/**
 * Created by Denis Shishkin on 28.06.2021.
 * qwq2eq@gmail.com
 */

data class RetrofitItemDeadline(
	val _id: String,
	val title: String,
	val description: String,
	val startDate: Int,
	val endDate: Int,
	val isExternal: Boolean,
	val isOutdated: Boolean,
	val type: RetrofitEnumDeadlineType,
	val subject: RetrofitItemSubject,
	val curPoints: Int,
	val markpoint: Int,
	val reportRequired: Boolean
): UniqueImportSource {
	override fun id(): String { return _id }
}