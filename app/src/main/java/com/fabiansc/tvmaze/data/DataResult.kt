package com.fabiansc.tvmaze.data


sealed class DataResult<out T, out E> {
    data class Success<out T>(val data: T) : DataResult<T, Nothing>()
    data class Error<out E>(val errorType: DataResultError) : DataResult<Nothing, E>()
    object Loading : DataResult<Nothing, Nothing>()
}

sealed class DataResultError {
    object NoError : DataResultError()
    object EmptyResult : DataResultError()
    object NoInternet : DataResultError()
}
