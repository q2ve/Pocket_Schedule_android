package com.q2ve.suai.helpers.contentGetter

import com.q2ve.suai.helpers.realm.RealmIO
import com.q2ve.suai.helpers.realm.RealmInterface
import com.q2ve.suai.helpers.realm.RealmObjectConverter
import com.q2ve.suai.helpers.realm.objects.IndexerItem
import com.q2ve.suai.helpers.realm.objects.IndexerItemType
import com.q2ve.suai.helpers.retrofit.RetrofitExecutor
import com.q2ve.suai.helpers.retrofit.RetrofitInterface
import com.q2ve.suai.helpers.retrofit.objects.RetrofitItemLesson
import com.q2ve.suai.helpers.retrofit.objects.RetrofitItemScheduleUser
import com.q2ve.suai.helpers.retrofit.objects.RetrofitItemUniversity

/**
 * Created by Denis Shishkin on 20.06.2021.
 * qwq2eq@gmail.com
 */

class ContentGetter(private val parent: ContentGetterInterface): RetrofitInterface, RealmInterface {
	private lateinit var contentType: ContentType

	fun getGroups(offset: Int, limit: Int, universityID: Int, query: String = "") {
		contentType = ContentType.Groups
		RetrofitExecutor(this).getGroups(offset, limit, universityID, query)
	}

	fun getProfessors(offset: Int, limit: Int, universityID: Int, query: String = "") {
		contentType = ContentType.Professors
		RetrofitExecutor(this).getProfessors(offset, limit, universityID, query)
	}

	fun getUniversities() {
		contentType = ContentType.Universities
		RetrofitExecutor(this).getUniversities()
	}

	fun getLessons(scheduleUserId: String) {
		contentType = ContentType.Lessons
		RetrofitExecutor(this).getLessons(scheduleUserId)
	}

	override fun <T> retrofitCallback(data: List<T>, isError: Boolean, t: Throwable?) {
		if (isError) {
			TODO("Обработчик ошибок ретрофита")
		} else {
			when (contentType) {
				ContentType.Groups -> {
					RealmIO().insertOrUpdateWithIndexer(
						this,
						IndexerItemType.Groups,
						RealmObjectConverter().convertScheduleUsersToRealm(data as List<RetrofitItemScheduleUser>)
					)
				}
				ContentType.Professors -> {
					RealmIO().insertOrUpdateWithIndexer(
						this,
						IndexerItemType.Professors,
						RealmObjectConverter().convertScheduleUsersToRealm(data as List<RetrofitItemScheduleUser>)
					)
				}
				ContentType.Universities -> {
					RealmIO().insertOrUpdateWithIndexer(
						this,
						IndexerItemType.Universities,
						RealmObjectConverter().convertUniversitiesToRealm(data as List<RetrofitItemUniversity>)
					)
				}
				ContentType.Lessons -> {
					RealmIO().insertOrUpdate(
						this,
						RealmObjectConverter().convertLessonsToRealm(data as List<RetrofitItemLesson>)
					)
				}
			}
		}
	}

	override fun realmCallback(inputData: List<Any>?, isError: Boolean, throwable: Throwable?) {
		if (isError) {
			TODO("Обработчик ошибок рилма")
		} else {
			parent.contentGetterCallback(inputData)
		}
	}

	private fun sortContentObjects(inputData: List<IndexerItem>) {
		val outputData: List<IndexerItem>
	}
}