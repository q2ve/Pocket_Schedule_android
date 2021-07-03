package com.q2ve.suai.helpers.retrofit.objects

import com.q2ve.suai.helpers.realm.UniqueImportSource

/**
 * Created by Denis Shishkin on 28.06.2021.
 * qwq2eq@gmail.com
 */

data class RetrofitItemSubject(
	val _id: String,
	val name: String,
	val deadlines: List<RetrofitItemDeadline>
) : UniqueImportSource {
	override fun id(): String { return _id }
}