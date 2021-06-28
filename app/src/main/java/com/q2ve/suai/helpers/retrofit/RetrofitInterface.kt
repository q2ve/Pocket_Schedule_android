package com.q2ve.suai.helpers.retrofit


/**
 * Created by Denis Shishkin on 11.06.2021.
 * qwq2eq@gmail.com
 */

interface RetrofitInterface  {
	fun <T> retrofitCallback(data: List<T>, isError: Boolean = false, t: Throwable? = null)
}