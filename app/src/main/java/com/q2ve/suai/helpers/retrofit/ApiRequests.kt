package com.q2ve.suai.helpers.retrofit

import com.q2ve.suai.helpers.retrofit.objects.RetrofitItemScheduleUser
import com.q2ve.suai.helpers.retrofit.objects.RetrofitItemUniversity
import com.q2ve.suai.helpers.retrofit.objects.RetrofitResponseObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiRequests {
	@GET("objects/groups?")
	fun getGroups(
		@Query("offset") offset: Int,
		@Query("limit") limit: Int,
		@Query("universityId") universityId: Int,
		@Query("q") q: String = ""
	): Call<RetrofitResponseObject<RetrofitItemScheduleUser>>

	@GET("objects/universities?")
	fun getUniversities(
		@Query("offset") offset: Int,
		@Query("limit") limit: Int,
		@Query("q") q: String = ""
	): Call<RetrofitResponseObject<RetrofitItemUniversity>>

	@POST("auth/service1")
	fun postUser(@Body login: String, password: String)

	@POST("auth/vk")
	fun postVkUser(@Body token: String)
}