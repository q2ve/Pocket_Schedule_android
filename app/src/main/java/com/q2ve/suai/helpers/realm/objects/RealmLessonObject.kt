package com.q2ve.suai.helpers.realm.objects

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by Denis Shishkin on 01.07.2021.
 * qwq2eq@gmail.com
 */

open class RealmLessonObject(
	@PrimaryKey
	var _id: String = "",
	var startTime: String? = null,
	var endTime: String? = null,
	var lessonNum: String = "",
	var day: String? = null,
	var rooms: String = "",
	var type: String? = null,
	var week: String? = null,
	var tags: String? = null,
	var groups: RealmList<RealmScheduleUserObject>? = RealmList(),
	var professors: RealmList<RealmScheduleUserObject>? = RealmList(),
	var subject: RealmSubjectObject? = null
): RealmObject()