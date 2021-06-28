package com.q2ve.suai.helpers.retrofit.objects

data class RetrofitResponseObject <type>(
    val error: Any?,
    val result: RetrofitResultObject <type>
)