package org.includejoe.markety.base.util

sealed class Response<T>(val data: T? = null, val message: Any? = null) {
    class Success<T>(data: T): Response<T>(data)
    class Error<T>(message: Any, data: T? = null): Response<T>(data, message)
    class Loading<T>(data: T? = null): Response<T>(data)
}
