package com.smparkworld.data.repository

import com.smparkworld.data.source.remote.WishRemoteDataSource
import com.smparkworld.domain.repository.WishRepository
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WishRepositoryImpl @Inject constructor(
    private val remoteDataSource: WishRemoteDataSource
) : WishRepository {

    private val cachedWishStates: ConcurrentMap<Long, Boolean> = ConcurrentHashMap()

    override suspend fun createWish(id: Long): Boolean {
        return remoteDataSource.createWish(id)
    }

    override suspend fun deleteWish(id: Long): Boolean {
        return remoteDataSource.deleteWish(id)
    }

    override suspend fun cacheWishState(id: Long, isWished: Boolean) {
        cachedWishStates[id] = isWished
    }

    override suspend fun getCachedWishState(id: Long): Boolean? {
        return cachedWishStates[id]
    }
}