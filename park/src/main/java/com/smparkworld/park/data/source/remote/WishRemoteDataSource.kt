package com.smparkworld.park.data.source.remote

import com.smparkworld.hiltbinder.HiltBinds
import kotlinx.coroutines.delay
import javax.inject.Inject

interface WishRemoteDataSource {

    suspend fun createWish(id: Long): Boolean

    suspend fun deleteWish(id: Long): Boolean
}

@HiltBinds
class WishRemoteDataSourceFakeImpl @Inject constructor(

) : WishRemoteDataSource {


    override suspend fun createWish(id: Long): Boolean {
        if (id == 777L) {
            delay(200L)
            throw Exception("Test Exception")
        }
        return true
    }

    override suspend fun deleteWish(id: Long): Boolean {
        return false
    }
}