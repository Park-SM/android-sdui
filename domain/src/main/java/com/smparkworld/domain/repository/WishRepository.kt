package com.smparkworld.domain.repository

interface WishRepository {

    suspend fun createWish(id: Long): Boolean

    suspend fun deleteWish(id: Long): Boolean

    suspend fun cacheWishState(id: Long, isWished: Boolean)

    suspend fun getCachedWishState(id: Long): Boolean?
}