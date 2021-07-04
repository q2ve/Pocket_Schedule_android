package com.q2ve.suai.helpers.network

import android.util.Log
import com.q2ve.suai.Constants
import com.q2ve.suai.helpers.retrofit.ApiRequests
import com.q2ve.suai.helpers.retrofit.objects.RetrofitResponseObject
import com.q2ve.suai.helpers.retrofit.objects.RetrofitResultObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

data class ResponseError(
	val id: Double,
	val code: String,
	val message: String?,
	val data: Any?
)

data class ResponseObject<ResponseType>(
	val error: ResponseError?,
	val result: ResponseType
)
data class ListResponse<ListType>(
	val items: List<ListType>,
	val totalCount: Int
)

enum class ApiError {
	uncommonError, connectionError,

	serverError,forbidden, invalidApiVersion, notFound, validation, tooOften
}
interface ApiResponseReceiver<ResponseType> {
	fun success(obj: ResponseType)
	fun error(throwable: Throwable?, error: ApiError)
}
class RetrofitProvider
{
	val retrofitInstance = Retrofit.Builder()
		.baseUrl(Constants.baseServerUrl)
		.addConverterFactory(GsonConverterFactory.create())
		.build()
	inline fun <reified RequestObject,ResponseType> request(
		apiRequest: (RequestObject) -> Call<ResponseObject<ResponseType>>,
		callbackReceiver: ApiResponseReceiver<ResponseType>
	)
	{
		val request = retrofitInstance.create(RequestObject::class.java)
		val call = apiRequest(request)

		call.enqueue(object : Callback<ResponseObject<ResponseType>>{
			override fun onFailure(call: Call<ResponseObject<ResponseType>>, t: Throwable) {
				callbackReceiver.error(t, ApiError.serverError)
			}

			override fun onResponse(
				call: Call<ResponseObject<ResponseType>>,
				response: Response<ResponseObject<ResponseType>>
			) {
				val errorObj = response.body()?.error
				val result = response.body()?.result

				if( errorObj  != null) {
					val error = when(errorObj.message) {
						"forbidden" -> ApiError.forbidden
						"invalid_api_version" -> ApiError.invalidApiVersion
						"too_often" -> ApiError.tooOften
						"not_found" -> ApiError.notFound
						"validation_error" -> ApiError.validation
						else -> ApiError.serverError
					}
					callbackReceiver.error(null, error)

				} else if ( result != null ) {
					callbackReceiver.success(result)
				}
			}

		})
	}
}
