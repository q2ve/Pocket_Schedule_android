package com.q2ve.suai.helpers.realm.objects

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.UUID.randomUUID

/**
 * Created by Denis Shishkin on 20.06.2021.
 * qwq2eq@gmail.com
 */

open class IndexerItem (
	@PrimaryKey
	var _id: String = randomUUID().toString(),
	var index: Int = 0,
	var name: String= "",
	var university: RealmUniversityObject? = null,
	var scheduleUser: RealmScheduleUserObject? = null
): RealmObject()