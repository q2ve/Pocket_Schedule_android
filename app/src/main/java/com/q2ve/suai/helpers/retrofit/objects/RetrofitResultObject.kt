package com.q2ve.suai.helpers.retrofit.objects

data class RetrofitResultObject <type>(
    val totalCount: Int,
    val items: List<type>
)