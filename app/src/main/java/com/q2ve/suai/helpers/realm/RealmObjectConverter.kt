package com.q2ve.suai.helpers.realm

import com.q2ve.suai.helpers.realm.objects.RealmScheduleUserObject
import com.q2ve.suai.helpers.realm.objects.RealmUniversityObject
import com.q2ve.suai.helpers.retrofit.objects.RetrofitItemScheduleUser
import com.q2ve.suai.helpers.retrofit.objects.RetrofitItemUniversity

/**
 * Created by Denis Shishkin on 20.06.2021.
 * qwq2eq@gmail.com
 */

class RealmObjectConverter {
	fun convertUniversitiesToRealm (input: List<RetrofitItemUniversity>): List<RealmUniversityObject> {
		var output: List<RealmUniversityObject> = emptyList()
		input.forEach {
			val realmObject = RealmUniversityObject()
			realmObject._id = it._id
			realmObject.referenceDate = it.referenceDate
			realmObject.referenceWeek = it.referenceWeek
			realmObject.universityName = it.universityName
			realmObject.universityService = it.universityService
			output += realmObject
		}
		return output
	}

	fun convertScheduleUsersToRealm (input: List<RetrofitItemScheduleUser>): List<RealmScheduleUserObject> {
		var output: List<RealmScheduleUserObject> = emptyList()
		input.forEach {
			val realmObject = RealmScheduleUserObject()
			realmObject._id = it._id
			realmObject.name = it.name
			output += realmObject
		}
		return output
	}
}