package com.q2ve.suai.helpers.retrofit

/**
 * Created by Denis Shishkin on 20.06.2021.
 * qwq2eq@gmail.com
 */

import android.util.Log
import com.q2ve.suai.helpers.retrofit.objects.RetrofitResponseObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitExecutor (val parent: RetrofitInterface) {
	private fun setupRequest(): ApiRequests {
		return Retrofit.Builder()
			.baseUrl("https://Restfulapi.ru/api/v1/")
			.addConverterFactory(GsonConverterFactory.create())
			.build()
			.create(ApiRequests::class.java)
	}

	private fun <T> sendCall(call: Call<RetrofitResponseObject<T>>) {
		call.enqueue(object : Callback<RetrofitResponseObject<T>> {
			override fun onResponse(
				call: Call<RetrofitResponseObject<T>>,
				response: Response<RetrofitResponseObject<T>>
			) {
				//Log.d("------Response items", response.body()!!.result.items.toString())
				//val test = RealmObjectConverter().convertScheduleUsersToRealm(response.body()!!.result.items as List<RetrofitItemScheduleUser>)
				//Log.d("---Response for realm", test.toString())
				Log.d("--RetrofitExecutor", "Successful response")
				parent.retrofitCallback(response.body()!!.result.items)
			}
			override fun onFailure(
				call: Call<RetrofitResponseObject<T>>,
				t: Throwable
			) {
				Log.d("--RetrofitExecutor", t.toString())
				parent.retrofitCallback(listOf(null), true, t)
			}
		})
	}

	fun getGroups() {
		val request = setupRequest()
		val call = request.getGroups(0, 8, 1)
		sendCall(call)
	}

	fun getUniversities() {
		val request = setupRequest()
		val call = request.getUniversities(0, 8)
		sendCall(call)
	}

	fun getLessons(scheduleUserId: String) {
		val request = setupRequest()
		sendCall(request.getLessons(scheduleUserId))
	}
}