package com.smparkworld.data.source.remote

import kotlinx.coroutines.delay
import javax.inject.Inject

interface WishRemoteDataSource {

    suspend fun createWish(id: Long): Boolean

    suspend fun deleteWish(id: Long): Boolean
}

class FakeWishRemoteDataSourceImpl @Inject constructor(

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