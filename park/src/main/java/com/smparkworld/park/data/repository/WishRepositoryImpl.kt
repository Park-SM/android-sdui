package com.smparkworld.park.data.repository

import com.smparkworld.hiltbinder.HiltBinds
import com.smparkworld.park.data.source.remote.WishRemoteDataSource
import com.smparkworld.park.domain.repository.WishRepository
import javax.inject.Inject

@HiltBinds
class WishRepositoryImpl @Inject constructor(
    private val remoteDataSource: WishRemoteDataSource
) : WishRepository {

    override suspend fun createWish(id: Long): Boolean {
        return remoteDataSource.createWish(id)
    }

    override suspend fun deleteWish(id: Long): Boolean {
        return remoteDataSource.deleteWish(id)
    }
}