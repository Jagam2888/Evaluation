package com.jagad.evaluation.network

/**
 * Created by jagad on 1/12/2022
 */
sealed class NetworkResult(
    val message: String?=null
){
    class Loading : NetworkResult()
    class Success(message:String):NetworkResult(message)
    class Failure(message: String):NetworkResult(message)
}
