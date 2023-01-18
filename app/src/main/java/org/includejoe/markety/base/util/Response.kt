package org.includejoe.markety.base.util

sealed class Response<T>(val data: T? = null, val message: Int? = null) {
    class Success<T>(data: T): Response<T>(data)
    class Error<T>(message: Int, data: T? = null): Response<T>(data, message)
    class Loading<T>(data: T? = null): Response<T>(data)
}
