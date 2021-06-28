package com.q2ve.suai.helpers.realm

/**
 * Created by Denis Shishkin on 20.06.2021.
 * qwq2eq@gmail.com
 */
interface RealmInterface {
	fun realmCallback (inputData: List<Any>? = null, isError: Boolean = false, throwable: Throwable? = null)
}