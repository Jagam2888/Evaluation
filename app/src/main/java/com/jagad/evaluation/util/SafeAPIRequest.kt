package com.jagad.evaluation.util

import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

abstract class SafeAPIRequest{
    suspend fun <T:Any>apiRequest(call : suspend () -> Response<T>): T {
        val response = call.invoke()
        if(response.isSuccessful){
            return response.body()!!
        }else{
            val error = response.errorBody()?.string()
            val msg = StringBuilder()
            error?.let {
                try {
                    val jsonObject = JSONObject(it).getJSONObject("fault")
                    msg.append(jsonObject.getString("faultstring"))
                }catch (e : JSONException){

                }
            }

            throw APIException(msg.toString())
        }
    }
}