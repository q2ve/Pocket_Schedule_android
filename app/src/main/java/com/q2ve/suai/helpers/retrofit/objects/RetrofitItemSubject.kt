package com.q2ve.suai.helpers.retrofit.objects

/**
 * Created by Denis Shishkin on 28.06.2021.
 * qwq2eq@gmail.com
 */

data class RetrofitItemSubject(
	val _id: String,
	val name: String,
	val deadlines: List<RetrofitItemDeadline>
)