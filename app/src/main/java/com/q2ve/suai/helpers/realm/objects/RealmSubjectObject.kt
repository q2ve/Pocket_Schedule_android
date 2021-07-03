package com.q2ve.suai.helpers.realm.objects

/**
 * Created by Denis Shishkin on 01.07.2021.
 * qwq2eq@gmail.com
 */

import com.q2ve.suai.helpers.realm.ImportableObject
import com.q2ve.suai.helpers.retrofit.objects.RetrofitItemDeadline
import com.q2ve.suai.helpers.retrofit.objects.RetrofitItemSubject
import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RealmSubjectObject (
	@PrimaryKey
	var _id: String = "",
	var name: String = "",
	var deadlines: RealmList<RealmDeadlineObject>? = RealmList()
): RealmObject(), ImportableObject<RetrofitItemSubject> {
	override fun id(): String { return _id }
	override fun setup(source: RetrofitItemSubject, inTransaction: Realm) {
		_id = source._id
		name = source.name

	}

}