package com.stone.acecodetest.utils

sealed class ApiResponse<out T>(val status: Status, val data: T?, val message: String?) {
    data class Success<out R>(val _data: R?) : ApiResponse<R>(
        status = Status.SUCCESS,
        data = _data,
        message = null
    )

    data class Error<out R>(val error: String) : ApiResponse<R>(
        status = Status.ERROR,
        data = null,
        message = error
    )
    data class NetWorkError<out R>(val error: String):ApiResponse<R>(
        status = Status.NETWORK_ERROR,
        data = null,
        message = error
    )

}