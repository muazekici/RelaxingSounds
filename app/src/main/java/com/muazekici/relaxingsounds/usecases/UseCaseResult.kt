package com.muazekici.relaxingsounds.usecases

sealed class UseCaseResult<out R> {

    data class Success<out T>(val data: T) : UseCaseResult<T>()
    data class Error(val exception: Exception) : UseCaseResult<Nothing>()
    object Loading : UseCaseResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }
}

val UseCaseResult<*>.succeeded
    get() = this is UseCaseResult.Success && data != null

fun <T> UseCaseResult<T>.successOr(fallback: T): T {
    return (this as? UseCaseResult.Success<T>)?.data ?: fallback
}

fun <T> UseCaseResult<T>.ifSuccess(op: ((T) -> Unit)) {
    if (this is UseCaseResult.Success) op(this.data)
}

fun UseCaseResult<Any>.ifError(op: ((e: Exception) -> Unit)) {
    if (this is UseCaseResult.Error) op(this.exception)
}