package com.q2ve.suai.helpers.contentGetter

/**
 * Created by Denis Shishkin on 20.06.2021.
 * qwq2eq@gmail.com
 */
interface ContentGetterInterface {
	fun contentGetterCallback (objects: List<Any>? = null, isError: Boolean = false, errorMessage: String? = null)
}