package com.muazekici.relaxingsounds.usecases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.CoroutineScope
import java.lang.Exception

abstract class UseCase<in P, R> {

    suspend operator fun invoke(parameter: P, callback: MutableLiveData<UseCaseResult<R>>) {
        callback.value = UseCaseResult.Loading
        try {
            callback.value = UseCaseResult.Success(execute(parameter))
        } catch (e: Exception) {
            callback.value = handleError(e)
        }
    }

    operator fun invoke(
        coroutineScope: CoroutineScope,
        parameter: P
    ): LiveData<UseCaseResult<R>> = liveData(coroutineScope.coroutineContext) {
        emit(UseCaseResult.Loading)
        try {
            emit(UseCaseResult.Success(execute(parameter)))
        } catch (e: Exception) {
            emit(handleError(e))
        }
    }

    suspend operator fun invoke(parameter: P): R {
        return execute(parameter)
    }

    open fun handleError(e: Exception): UseCaseResult<R> {
        return UseCaseResult.Error(e)
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(parameter: P): R
}

suspend operator fun <R> UseCase<Unit, R>.invoke(result: MutableLiveData<UseCaseResult<R>>) =
    this(Unit, result)