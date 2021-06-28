package com.q2ve.suai.helpers.realm.objects

/**
 * Created by Denis Shishkin on 20.06.2021.
 * qwq2eq@gmail.com
 */

enum class IndexerItemType {
	Professors, Groups, Universities, Deadlines;

	fun getField() : String {
		return when (this) {
			Groups -> "scheduleUser"
			Professors -> "scheduleUser"
			Universities -> "university"
			Deadlines -> "deadlines"
		}
	}
}