package com.muazekici.relaxingsounds.usecases

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart

abstract class FlowUseCase<in P, R> {

    abstract fun execute(parameter: P): Flow<UseCaseResult<R>>

    fun run(parameter: P):Flow<UseCaseResult<R>>{
        return execute(parameter).onStart {
            emit(UseCaseResult.Loading)
            delay(1000)
        }
    }
}