package com.q2ve.suai.helpers.realm

import com.q2ve.suai.helpers.realm.objects.RealmLessonObject
import com.q2ve.suai.helpers.realm.objects.RealmScheduleUserObject
import com.q2ve.suai.helpers.realm.objects.RealmSubjectObject
import com.q2ve.suai.helpers.realm.objects.RealmUniversityObject
import com.q2ve.suai.helpers.retrofit.objects.RetrofitItemLesson
import com.q2ve.suai.helpers.retrofit.objects.RetrofitItemScheduleUser
import com.q2ve.suai.helpers.retrofit.objects.RetrofitItemSubject
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

	fun convertLessonsToRealm (input: List<RetrofitItemLesson>): List<RealmLessonObject> {
		var output: List<RealmLessonObject> = emptyList()
		input.forEach {
			val realmObject = RealmLessonObject()
			realmObject._id = it._id
			realmObject.startTime = it.startTime
			realmObject.endTime = it.endTime
			realmObject.lessonNum = it.lessonNum
			realmObject.day = it.day
			realmObject.rooms = it.rooms
			realmObject.type = it.type
			realmObject.week = it.week
			realmObject.tags = it.tags
			if(it.groups == null) {
				realmObject.groups = null
			} else {
				convertScheduleUsersToRealm(it.groups).forEach { item ->
					realmObject.groups!!.add(item)
				}
			}
			if(it.professors == null) {
				realmObject.professors = null
			} else {
				convertScheduleUsersToRealm(it.professors).forEach { item ->
					realmObject.professors!!.add(item)
				}
			}
			realmObject.subject = convertSubjectToRealm(it.subject)

			output += realmObject
		}
		return output
	}

	fun convertSubjectToRealm (input: RetrofitItemSubject): RealmSubjectObject {
		val realmObject = RealmSubjectObject()
		realmObject._id = input._id
		realmObject.name = input.name
		realmObject.deadlines = null
		return realmObject
	}
}