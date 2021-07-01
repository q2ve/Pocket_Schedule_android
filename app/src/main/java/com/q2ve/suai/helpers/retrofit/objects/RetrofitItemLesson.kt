package com.q2ve.suai.helpers.retrofit.objects

/**
 * Created by Denis Shishkin on 28.06.2021.
 * qwq2eq@gmail.com
 */

data class RetrofitItemLesson(
    val _id: String,
    val startTime: String?,
    val endTime: String?,
    val lessonNum: String,
    val day: String?,
    val rooms: String,
    val type: String?,
    val week: String?,
    val tags: String?,
    val groups: List<RetrofitItemScheduleUser>?,
    val professors: List<RetrofitItemScheduleUser>?,
    val subject: RetrofitItemSubject
)