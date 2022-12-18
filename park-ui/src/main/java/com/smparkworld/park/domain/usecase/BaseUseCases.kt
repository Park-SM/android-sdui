package com.smparkworld.park.domain.usecase

import com.smparkworld.park.BuildConfig
import com.smparkworld.core.model.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

interface UseCase

abstract class UseCaseWithParam<in P, R>(
    private val dispatcher: CoroutineDispatcher
) : UseCase {

    suspend operator fun invoke(parameter: P): Result<R> {
        return try {
            withContext(dispatcher) {
                execute(parameter).let { result ->
                    Result.Success(result)
                }
            }
        } catch (e: Exception) {
            if (BuildConfig.DEBUG) e.printStackTrace()
            Result.Error(e)
        }
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(parameters: P): R
}

abstract class UseCaseWithoutParam<R>(
    private val dispatcher: CoroutineDispatcher
) : UseCase {

    suspend operator fun invoke(): Result<R> {
        return try {
            withContext(dispatcher) {
                execute().let { result ->
                    Result.Success(result)
                }
            }
        } catch (e: Exception) {
            if (BuildConfig.DEBUG) e.printStackTrace()
            Result.Error(e)
        }
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(): R
}